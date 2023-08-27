package br.kotlin.app.infrastructure.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition
@Configuration
class OpenApiConfig {

    private val version : String = "1.0.0";

    @Bean
    fun customOpenAPI(): OpenAPI {
        val info: Info = Info()
            .title("Documentação de API")
            .description("Documentação de API do Cliente")
            .version(version)
        return OpenAPI().components(Components())
            .info(info)
    }

    fun createTag(name: String?, description: String?): Tag? {
        val tag = Tag()
        tag.setName(name)
        tag.setDescription(description)
        return tag
    }
}