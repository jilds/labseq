package com.jilds.labseq.exception;

import com.jilds.labseq.exception.dto.APIError;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import java.util.Objects;

@Log4j2(topic = "LabseqService")
@RestControllerAdvice
public class RestResponseExceptionHandler extends GlobalExceptionHandler {


    @ApiResponse(responseCode = "500", ref = "500")
    @ExceptionHandler(value = {LabseqException.class})
    protected ResponseEntity<Object> onLabseqException(LabseqException ex, WebRequest request) {
        HttpStatus status = (Objects.isNull(ex.getHttpStatus())) ? HttpStatus.INTERNAL_SERVER_ERROR : ex.getHttpStatus();
        APIError errorBody;
        if (Objects.nonNull(ex.getApiError())) {
            errorBody = ex.getApiError();
        } else {
            errorBody = APIError.builder()
                    .name(status.name())
                    .message(ex.getMessage())
                    .build();
        }
        log.error(errorBody.getMessage());
        return handleExceptionInternal(ex, errorBody, new HttpHeaders(), status, request);
    }
}