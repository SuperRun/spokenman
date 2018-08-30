package com.zust.itee.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 登录拦截器
 *
 * @author pojun
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("sessionInfo") == null) {
            response.sendRedirect(request.getContextPath() + "/index");
            return false;
        }
        return true;
    }
}
