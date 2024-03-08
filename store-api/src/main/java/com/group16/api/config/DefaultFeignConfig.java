package com.group16.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    // This configuration is used to choose feign logging level
    @Bean
    public feign.Logger.Level feignLoggerLevel() {
        return Logger.Level.NONE;
    }
}
