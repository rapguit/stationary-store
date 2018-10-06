package com.store.stationary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class BeansConfig {

    @Bean
    public WebMvcConfigurer configurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers (ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/stationary-store/**")
                        .addResourceLocations("classpath:/templates/", "classpath:/static/");
            }
        };
    }
}
