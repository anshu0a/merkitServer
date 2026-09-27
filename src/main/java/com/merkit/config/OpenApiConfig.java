package com.merkit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI openAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("MerKit Auth Service")
                        .version("1.0")
                        .description("Authentication APIs")
                        .contact(new Contact()
                                .name("MerKit Team")
                                .email("support@merkit.com")));

    }

}