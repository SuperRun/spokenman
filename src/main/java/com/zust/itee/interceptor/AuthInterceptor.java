package com.zust.itee.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zust.itee.dto.base.SessionInfo;
import com.zust.itee.enums.user.UserStatusType;
import com.zust.itee.enums.user.UserType;

import lombok.extern.slf4j.Slf4j;

/**
 * 权限控制器
 *
 * @author pojun
 */
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    // 平台用户前缀
    private final static String PLATFORM_PREFIX = "/platform";

    // 单位管理员前缀
    private final static String UNIT_USER_PREFIX = "/unitUser";

    // 平台/单位管理员前缀
    private final static String PU_USER_PREFIX = "/puUser";

    // 个人用户前缀
    private final static String PRR_USER_PREFIX = "/perUser";

    // 首页 url
    private final static String INDEX = "/index";

    // 提示验证资料 url
    private final static String TIP = "/perUser/applyTip";

    // 信息验证 url
    private final static String AUTHENTICATE = "/perUser/authenticate";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler)
            throws Exception {
        String url = request.getRequestURI();
        HttpSession session = request.getSession();
        SessionInfo sessionInfo = (SessionInfo) session.getAttribute("sessionInfo");

        if (sessionInfo != null && !sessionInfo.getStatus()
                .equals(UserStatusType.NORMAL.getStatus()) &&
                (sessionInfo.getType().equals(UserType.PERSONAL.getValue())
                        || sessionInfo.getType().equals(UserType.COMPANY.getValue()))) {
            // 用户需要认证资料
            if (!AUTHENTICATE.equals(url) && !TIP.equals(url)) {
                log.debug("提示用户需要认证资料");
                redirectToPage(request, response, TIP);
                return false;
            }
        }

        if (INDEX.equals(url)) {
            return true;
        }

        if (url.startsWith(PLATFORM_PREFIX)) {
            log.debug("平台管理员页面");
            if (sessionInfo == null || UserType.ROOT.getValue() != sessionInfo.getType()) {
                redirectToPage(request, response, INDEX);
                return false;
            }
        } else if (url.startsWith(UNIT_USER_PREFIX)) {
            log.debug("单位管理员页面");
            if (sessionInfo == null || UserType.COMPANY.getValue() != sessionInfo.getType()) {
                redirectToPage(request, response, INDEX);
                return false;
            }
        } else if (url.startsWith(PU_USER_PREFIX)) {
            log.debug("平台/单位管理员页面");
            if (sessionInfo == null
                    || (UserType.COMPANY.getValue() != sessionInfo.getType()
                    && UserType.ROOT.getValue() != sessionInfo.getType())) {
                redirectToPage(request, response, INDEX);
                return false;
            }
        }

        return true;
    }

    private void redirectToPage(HttpServletRequest request, HttpServletResponse response,
            String page) throws IOException {
        response.sendRedirect(request.getContextPath() + page);
    }
}
