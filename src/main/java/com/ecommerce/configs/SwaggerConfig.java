package com.ecommerce.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🛒 E-Commerce API")
                        .version("v1.0")
                        .description("This is a sample Spring Boot E-commerce backend with JWT authentication, product management, cart, and order APIs.")
                        .contact(new Contact()
                                .name("Yash Zade")
                                .email("yashmzade@gmail.com")
                                .url("https://github.com/Yash-Zade/AdaptNXT_E-commerce")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
