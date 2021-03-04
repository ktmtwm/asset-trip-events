package com.phocas.asset.rest.config;


import com.phocas.asset.rest.middle.AssetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;


@Configuration
public class ProviderConfig {
    @Bean
    @RequestScope
    public AssetProvider getEventProvider() {
        return new AssetProvider();
    }

    @Bean
    public Logger getLogger() {
        return LoggerFactory.getLogger("asset-rest");
    }
}
