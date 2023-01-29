package org.hsh.sb01.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@OpenAPIDefinition(
        info = @Info(
            title= "spring-boot reply 명세",
            description = "API 명세",
            version = "v1",
            contact = @Contact(
                    name="hhh",
                    email = ""
            )
        )
)
@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi sampleGroupApi(){
        String[] paths = {"/replies/**"};

        return GroupedOpenApi.builder().group("게시판 댓글 관련 API").pathsToMatch(paths).build();
    }
}
