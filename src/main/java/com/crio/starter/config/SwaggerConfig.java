package com.crio.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        Docket docket= new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(getAPIInfo());
        return docket;
    }

    private ApiInfo getAPIInfo() {
        String version = "SPRINGBOOT-2.7.1 and Java-11";
        String descriptionString = "I am excited to announce that I have successfully created the XMeme backend using Spring Boot and Using MongoDB as database, exposing comprehensive API documentation through Swagger. Additionally, I have containerized the entire project using Docker, ensuring seamless deployment and scalability.";

        Contact contact = new Contact(
                "Tanuj",
                "https://www.linkedin.com/in/tanuj-arora-delhi2703/",
                "tanujarora2703@gmail.com"
        );

        return new ApiInfo(
                "XMEME",
                descriptionString,
                version,
                "",
                contact,
                "License",
                "License URL",
                new ArrayList<VendorExtension>()
        );
    }
}
