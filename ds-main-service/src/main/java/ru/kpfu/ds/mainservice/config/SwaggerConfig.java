package ru.kpfu.ds.mainservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.kpfu.ds.mainservice.model.constant.Constant;
import ru.kpfu.ds.mainservice.model.enums.TokenType;

import java.util.Collections;

@Slf4j
@Configuration
public class SwaggerConfig {

    @Value("${ds.app.url}")
    private String appUrl;

    @Value("${ds.app.api-path}")
    private String apiPath;

    @Bean
    public OpenAPI openApi() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name(TokenType.ACCESS_TOKEN.name());

        Components components = new Components()
                .addSecuritySchemes(Constant.BEARER, securityScheme);

        OpenAPI openAPI = new OpenAPI().components(components);

        if (StringUtils.isNotBlank(appUrl)) {
            openAPI.servers(Collections.singletonList(new Server().url(appUrl + apiPath)));
        }

        return openAPI;
    }

    @Bean
    public GroupedOpenApi groupedOpenApi() {
        return GroupedOpenApi.builder()
                .group("ui-api")
                .pathsToMatch("/api/v1/ui/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedAuthApi() {
        return GroupedOpenApi.builder()
                .group("auth-api")
                .pathsToMatch("/api/v1/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi groupedUserApi() {
        return GroupedOpenApi.builder()
                .group("user-api")
                .pathsToMatch("/api/v1/users/**")
                .build();
    }
}