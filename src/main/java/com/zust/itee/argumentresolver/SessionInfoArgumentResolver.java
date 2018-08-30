package com.zust.itee.argumentresolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.zust.itee.dto.base.SessionInfo;

public class SessionInfoArgumentResolver implements WebArgumentResolver {

    public Object resolveArgument(MethodParameter methodParameter,
            NativeWebRequest webRequest) throws Exception {
        if (methodParameter.getParameterType().equals(SessionInfo.class)) {
            return webRequest.getAttribute("sessionInfo",
                    RequestAttributes.SCOPE_SESSION);
        }

        return UNRESOLVED;
    }
}
