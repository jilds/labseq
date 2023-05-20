package com.jilds.labseq.configuration;

import com.jilds.labseq.exception.dto.APIError;
import com.jilds.labseq.exception.dto.ErrorDetail;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.text.MessageFormat;
import java.util.List;

@Configuration
public class OpenApiConfig {

    public static final String OPENAPI_TITLE_PATTERN = "Labseq API Service";
    public  static final String OPENAPI_DESCRIPTION_PATTER = "API to calculte the value of n using the fallowing methods:\n" +
            "- if n=0 => l(0) = 0\n" +
            "- if n=1 => l(1) = 1\n" +
            "- if n=2 => l(2) = 0\n" +
            "- if n=3 => l(3) = 1\n" +
            "- if n>3 => l(n) = l(n-4) + l(n-3)";
    @Bean(name="OpenAPI")
    public OpenAPI getOpenApiConfig(){
        OpenAPI oapi = OpenApiConfig.createOpenApiConfig();
        oapi.info(new Info().title(OPENAPI_TITLE_PATTERN)
                .description(OPENAPI_DESCRIPTION_PATTER)
                .version("V1"));
        return oapi;
    }

    private static OpenAPI createOpenApiConfig() {
        OpenAPI openApiConfig = new OpenAPI();

        openApiConfig.components(new Components()
                .addResponses("500",
                        new ApiResponse()
                                .description("Generic service error")
                                .content(new Content()
                                        .addMediaType(
                                                MediaType.APPLICATION_JSON_VALUE,
                                                new io.swagger.v3.oas.models.media.MediaType()
                                                        .example(APIError.builder()
                                                                .message("Error description")
                                                                .build())
                                        )))
                .addResponses("501",
                        new ApiResponse()
                                .description("When the request API has not been implemented yet."))
                .addResponses("404", new ApiResponse()
                        .description("When no resource exists for any supplied param"))
                .addResponses("400", new ApiResponse()
                        .description("When the request is malformed or any user input is invalid.")
                        .content(
                                new Content()
                                        .addMediaType(
                                                MediaType.APPLICATION_JSON_VALUE,
                                                new io.swagger.v3.oas.models.media.MediaType()
                                                        .example(APIError.builder()
                                                                .name("Http error name")
                                                                .message("Context specific message")
                                                                .details(List.of(ErrorDetail.builder()
                                                                        .issue("Describe field issue")
                                                                        .field("Identify bad input field")
                                                                        .value("Describe field value")
                                                                        .build()))
                                                                .build()
                                                        )
                                        )
                        ))
                .addResponses("422", new ApiResponse()
                        .description("When the input does not pass validation and is unprocessable.")
                        .content(
                                new Content()
                                        .addMediaType(
                                                MediaType.APPLICATION_JSON_VALUE,
                                                new io.swagger.v3.oas.models.media.MediaType()
                                                        .example(APIError.builder()
                                                                .name("Http error name")
                                                                .message("Context specific message")
                                                                .details(List.of(ErrorDetail.builder()
                                                                        .issue("Describe field issue")
                                                                        .field("Identify bad input field")
                                                                        .value("Describe field value")
                                                                        .build()))
                                                                .build()
                                                        )
                                        )
                        ))
        );

        return openApiConfig;
    }
}