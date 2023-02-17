package com.farmu.interview.service.urlshortener.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiV3Configuration {
    
    @Bean
    public OpenAPI customOpenAPI(
            @Value("${farmu.interview.service.urlshortener.version}") String appVersion,
            @Value("${farmu.interview.service.urlshortener.name}") String appName,
            @Value("${farmu.interview.termsOfService}") String appTermsOfService,
            @Value("${farmu.interview.license}") String appLicense
        ) {
        return new OpenAPI()
			.info(
                new Info()
                    .title(appName)
                    .version(appVersion)
                    .termsOfService(appTermsOfService)
                    .license(
                        new License()
                            .name("Private License")
                            .url(appLicense)
                    )
            );
    }

}
