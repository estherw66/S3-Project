package com.fontys.s3itproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorisedDataAccessException extends ResponseStatusException {
    public UnauthorisedDataAccessException(String errorCause) {
        super(HttpStatus.FORBIDDEN, errorCause);
    }
}
