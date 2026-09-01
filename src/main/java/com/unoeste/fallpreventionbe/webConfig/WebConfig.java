package com.unoeste.fallpreventionbe.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/fotos_exercicios/**")
                .addResourceLocations("file:uploads/fotos_exercicios/");

        registry.addResourceHandler("/videos_exercicios/**")
                .addResourceLocations("file:uploads/videos_exercicios/");
    }
}