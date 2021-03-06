package com.phocas.asset.rest.config;


import com.phocas.asset.rest.middle.AssetProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


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

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
