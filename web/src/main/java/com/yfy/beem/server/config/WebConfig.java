package com.yfy.beem.server.config;

import com.yfy.beem.server.util.WebRequestMappings;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC servlet configuration class for tomcat
 * */

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", WebRequestMappings.LIST_USERS);
    }
}
