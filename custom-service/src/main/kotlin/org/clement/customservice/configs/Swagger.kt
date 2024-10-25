package org.clement.customservice.configs

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.context.annotation.Configuration


@OpenAPIDefinition(
    info = Info(
        title = "Custom Service - KS",
        description = "OpenAPI docs for customer service",
        version = "1.0.0"
    )
)
@Configuration
class Swagger {
}