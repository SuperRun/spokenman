package com.zust.itee.exam.controller.base;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;

/**
 * 权限过滤
 * Created by dxb on 2016/10/13.
 */
public class LoginFilter implements Filter {

    public static final String SESSION_INFO_NAME = "sessionInfo";
    public static final Integer NO_LOGIN_SF_ID = -1;
    Set<String> allowedPreffix = new HashSet<>();
    Set<String> exception = new HashSet<>();
    Set<String> needLogin = new HashSet<>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String[] preffixses = filterConfig.getInitParameter("resourcePre").split(",");
        for (String t : preffixses) {
            allowedPreffix.add(t);
        }

        String[] needLogins = filterConfig.getInitParameter("needLogin").split(",");
        for (String n : needLogins) {
            needLogin.add(n);
        }

        String[] exceptions = filterConfig.getInitParameter("exception").split(",");
        for (String t : exceptions) {
            exception.add(t);
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute(LoginFilter.SESSION_INFO_NAME);

        logger.debug("拿到sessionInfo" + JSON.toJSONString(sessionInfo));

        //如果sessionInfo不存在 创建一个状态为未登录的sessionInfo
        if (sessionInfo == null) {
            sessionInfo = new SessionInfo(LoginFilter.NO_LOGIN_SF_ID);
            sessionInfo.setUa(request.getHeader("user-agent"));
            sessionInfo.setIp(request.getRemoteAddr());
            //添加没有登录的角色
            session.setAttribute(LoginFilter.SESSION_INFO_NAME, sessionInfo);
        }

        //请求路径处理
        String uri = getRidOfContextPathAndSuffix(request.getRequestURI(),
                request.getContextPath());

        //静态资源请求放行
        if (uri == null || "".equals(uri) || isResource(uri) || exception.contains(uri)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String prefix = getPrefix(uri);
        logger.debug("权限请求uri = " + uri + ",权限请求prefix = " + prefix);

        if (needLogin.contains(prefix) && (sessionInfo.getUserId() == null
                || sessionInfo.getUserId().equals(NO_LOGIN_SF_ID))) {
            session.setAttribute("lastVisit", uri);
            response.sendRedirect(request.getContextPath() + "/member/login");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getPrefix(String uri) {
        int i = uri.indexOf("/");
        if (i > 0) {
            return uri.substring(0, i);
        }
        return uri;
    }

    /**
     * 删除请求路径中的上下文路径
     *
     * @param uri 请求路径
     * @param contextPath 上下文路径
     * @return 匹配到控制器的请求路径
     */
    private String getRidOfContextPathAndSuffix(String uri, String contextPath) {
        uri = uri.substring(contextPath.length());

        if (uri.charAt(0) == '/') {
            uri = uri.substring(1);
        }
        int lastPoint = uri.lastIndexOf(".");
        if (lastPoint > 0) {
            uri = uri.substring(0, lastPoint);
        }
        return uri;
    }

    /**
     * 根据排除规则是否是静态资源
     *
     * @param uri 请求路径
     * @return 是静态资源
     */
    private boolean isResource(String uri) {
        String[] uris = uri.split("/");
        if (uris.length > 0) {
            if (allowedPreffix.contains(uris[0])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
