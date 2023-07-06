package com.hbsites.rpgtracker.coc.config;

import com.hbsites.hbsitescommons.config.ApiVersionRequestMappingHandlerMapping;
import com.hbsites.hbsitescommons.config.RequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.List;

@Configuration
@EnableSpringDataWebSupport
public class WebMvcConfig extends DelegatingWebMvcConfiguration {

    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        ApiVersionRequestMappingHandlerMapping apiVersionRequestMappingHandlerMapping = new ApiVersionRequestMappingHandlerMapping("/coc/api/v");
        apiVersionRequestMappingHandlerMapping.setInterceptors(new WebRequestHandlerInterceptorAdapter(new RequestInterceptor()));

        return apiVersionRequestMappingHandlerMapping;
    }

    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }
}
