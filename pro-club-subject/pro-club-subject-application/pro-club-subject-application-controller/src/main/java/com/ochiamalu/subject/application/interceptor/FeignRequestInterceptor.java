package com.ochiamalu.subject.application.interceptor;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * feign请求拦截器
 *
 * @author OchiaMalu
 * @date 2024/08/16
 */
@Component
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String loginId = request.getHeader("loginId");
        if (StringUtils.isNoneBlank(loginId)) {
            requestTemplate.header("loginId", loginId);
        }
    }
}
