package com.app.journeymemo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@SecurityScheme(
//        name = "Authorization",
//        scheme = "bearer",
//        bearerFormat = "JWT",
//        type = SecuritySchemeType.HTTP,
//        in = SecuritySchemeIn.HEADER
//)
public class SwaggerConfig {


    // todo: fill real data
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("journeymemoo@gmail.com");
        contact.setName("JourneyMemo");


        Info info = new Info()
                .title("Journey Memo API")
                .version("1.0")
                .contact(contact)
                .description("Endpoints of App.");

        return new OpenAPI().info(info);
//                .addSecurityItem(
//                new SecurityRequirement().addList("Authorization")
//        );
    }
}