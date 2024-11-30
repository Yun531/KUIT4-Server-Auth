package com.kuit.kuit4serverauth.interceptor;

import com.kuit.kuit4serverauth.dto.UserDetail;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.NativeWebRequest;

@Component
public class AuthArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserDetail.class);  // UserDetail 객체를 처리하려면 이 조건이 true여야 함
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String username = (String) request.getAttribute("username");
        String role = (String) request.getAttribute("role");

        // UserDetail 객체를 반환
        return new UserDetail(username, role);
    }
}
