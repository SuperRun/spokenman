package com.zust.itee.exam.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.zust.itee.dto.base.SessionInfo;

/**
 * Title: 蓝拓易<br>
 * Description: 登录拦截器<br>
 * Copyright: Copyright (c) 2009<br>
 * Company: DCS<br>
 *
 * @author alsa
 * @version 1.0
 */
public class WebsUserAuthorization implements Filter {

    /**
     * 日志
     */
    private static final Logger log = LoggerFactory.getLogger(WebsUserAuthorization.class);
    //无需登录即可访问的url
    private String[] noSessionUrls = null;
    //各登录角色的home页面的url
    private String[] homeUrls = null;
    private final String URL_SPLIT_PATTERN = "[, ;\r\n]";
    private final String[] NULL_STRING_ARRAY = new String[0];

    public void destroy() {
    }

    public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain fc)
            throws IOException,
            ServletException {

        HttpServletRequest request = (HttpServletRequest) request1;
        HttpServletResponse response = (HttpServletResponse) response1;
        SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute("sessionInfo");
        String url = request.getRequestURI();

        log.info("拦截url访问进行权限判断 : " + url);
        if (url.length() <= 1) {
            fc.doFilter(request, response);
            return;
        }
        boolean noSession = false;
        for (String noSessionUrl : noSessionUrls) {
            if (url.indexOf(noSessionUrl) >= 0) {
                noSession = true;
                break;
            }
        }
        if (noSession) {
            fc.doFilter(request, response);
            return;
        } else if (sessionInfo == null) {
            response.sendRedirect(request.getContextPath() + "/login.html");

            log.info("重定向到login页面");
            return;
        }
        // TODO 权限
        if (sessionInfo.getOrganizationId() == 1) {
            log.info("平台通过权限验证，正访问: " + url);
            fc.doFilter(request, response);
            return;
        }
        //去掉contextPath和后缀
        url = url.substring(request.getContextPath().length() + 1);
        url = url.substring(0, url.lastIndexOf("."));
        log.info("判断权限用的url: " + url);
        //log.info("request dispatcher type:"+request.get);
        String authUrlsString = sessionInfo.getAuthUrls();
        //log.info("用户权限"+authUrlsString);

        if (authUrlsString.indexOf(url) < 0) {

            //如果没有访问url的权限，则返回无权限页面noAuth.html
            boolean isHome = false;
            for (String home : homeUrls) {
                if (url.indexOf(home) >= 0) {
                    isHome = true;
                    log.info("无需权限验证的链接，正访问: " + url);
                    break;
                }
            }

            if (!isHome) {
                log.info("权限验证不通过，转向首页: " + url);
                response.sendRedirect(request.getContextPath() + "/goHome.html?noAuthUrl=" + url);
                return;
            }
        }

        log.info("权限验证通过，正访问: " + url);
        //具有访问该url的权限，可以通行
        fc.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        String noSessionUrlStr = config.getInitParameter("noSessionUrls");
        noSessionUrls = strToArray(noSessionUrlStr);
        String homeUrlStr = config.getInitParameter("homeUrls");
        homeUrls = strToArray(homeUrlStr);
        log.info(noSessionUrls + ":" + JSON.toJSONString(noSessionUrls));
        log.info(homeUrls + ":" + JSON.toJSONString(homeUrls));
    }

    private String[] strToArray(String urlStr) {
        if (urlStr == null) {
            return NULL_STRING_ARRAY;
        }
        String[] urlArray = urlStr.split(URL_SPLIT_PATTERN);
        List<String> urlList = new ArrayList<String>();
        for (String url : urlArray) {
            url = url.trim();
            if (url.length() == 0) {
                continue;
            }
            urlList.add(url);
        }
        return urlList.toArray(NULL_STRING_ARRAY);
    }
}
