package com.jilds.labseq.exception;

import com.jilds.labseq.exception.dto.APIError;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public abstract class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected GlobalExceptionHandler() {
    }

    @ApiResponse(responseCode = "501", ref = "501")
    @ExceptionHandler(value = {HttpServerErrorException.NotImplemented.class, UnsupportedOperationException.class})
    protected ResponseEntity<Object> handleNotImplemented(RuntimeException ex, WebRequest request) {
        APIError errorBody = APIError.builder().name(HttpStatus.NOT_IMPLEMENTED.name()).message("This API has not yet been implemented").build();
        return this.handleExceptionInternal(ex, errorBody, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }

}