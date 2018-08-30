package com.zust.itee.exceptionhandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zust.itee.dto.base.JsonResponse;
import com.zust.itee.exception.BusinessException;

import lombok.extern.slf4j.Slf4j;

/**
 * 统一异常处理
 *
 * @author pojun
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 业务异常统一处理
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public JsonResponse businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        log.warn("==请求 " + request.getRequestURI() + " 异常== params:{},e:{}",
                JSONObject.toJSONString(request.getParameterMap()), e);
        return JsonResponse.error(e.getMessage(), e.getCode());
    }

    /**
     * 异常处理
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse exceptionHandler(HttpServletRequest request, Exception e) {
        log.warn("==请求 " + request.getRequestURI() + " 出错== params:{},e:{}",
                JSONObject.toJSONString(request.getParameterMap()), e);
        return JsonResponse.error();
    }
}
