package dev.mayra.seeddesafiocdc.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

@Configuration
public class SwaggerConfig {

    @Value("${app.swagger.server-url}")
    private String swaggerServerUrl;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
        .info(new Info().title("Seed Desafio CDC API")
            .description("Docs")
            .version("v0.0.1")
            .license(new License().name("Apache 2.0").url("https://springdoc.org")))
            .servers(List.of(new Server().url(swaggerServerUrl)));
    }

}
