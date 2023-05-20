package com.jilds.labseq.exception;

import com.jilds.labseq.exception.dto.APIError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class LabseqException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;

    @Getter
    @Setter
    private APIError apiError;

    public LabseqException(String message) {
        super(message);
    }

    public LabseqException(String message, HttpStatus status) {
        super(message);
        this.httpStatus = status;
    }

    public LabseqException(APIError apiError) {
        super(apiError.getMessage());
        this.apiError = apiError;
    }

    public LabseqException(APIError apiError, HttpStatus status) {
        super(apiError.getMessage());
        this.apiError = apiError;
        this.httpStatus = status;
    }
}
