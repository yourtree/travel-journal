package com.gs.tj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 * Customizes the API documentation with project information and metadata.
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI tjOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Travel Journal API")
                        .description("API documentation for the Travel Journal application")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Travel Journal Team")
                                .email("support@traveljournal.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
} 