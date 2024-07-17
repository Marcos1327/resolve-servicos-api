package com.resolveservicos.configurations.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
public class SwaggerConfig {

    private final String URL_CONTROLS = "com.resolveservicos.controllers";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(URL_CONTROLS))
                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET());
    }

    private List<ResponseMessage> responseMessageForGET() {

        List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
        messages.add(new ResponseMessageBuilder()
                .code(500)
                .message("500 message")
                .responseModel(new ModelRef("error"))
                .build());

        messages.add(new ResponseMessageBuilder()
                .code(403)
                .message("forbidden!")
                .build());

        messages.add(new ResponseMessageBuilder()
                .code(201)
                .message("created")
                .build());

        return messages;
    }
}
