package com.anand.springproject.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * <a href="https://github.com/springfox/springfox/issues/3983">...</a>
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI().info(new Info()
                .title("Springproject Service")
                .description("Springproject Service")
                .version("2.0.0")
                .termsOfService("Terms of service")
                .contact(new Contact().name("Bikas Anand").email("email@email.com"))
                .license(new License().name("Test License Version 2.0").url("https://www.nolicense.test/licenses/license-2.0"))
        );
    }

    @Bean
    public GroupedOpenApi httpApi() {
        return GroupedOpenApi.builder()
                .group("https")
                .pathsToMatch("/**")
                .build();
    }
}
