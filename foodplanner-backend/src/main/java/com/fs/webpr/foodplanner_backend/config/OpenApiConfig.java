package com.fs.webpr.foodplanner_backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Gerzsenyi Patrik",
                        email = "q71lux@inf.elte.hu"
                ),
                title = "OpenAPI Specification",
                description = "OpenAPI Documentation for Food Planner Application",
                version = "0.0.1-SNAPSHOT"
        )
)
public class OpenApiConfig {
}
