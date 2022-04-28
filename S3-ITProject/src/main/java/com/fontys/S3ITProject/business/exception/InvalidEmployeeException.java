package com.fontys.s3itproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidEmployeeException extends ResponseStatusException {
    public InvalidEmployeeException() {
        super(HttpStatus.BAD_REQUEST, "EMPLOYEE_INVALID");
    }

    public InvalidEmployeeException(String errCode) {
        super(HttpStatus.BAD_REQUEST, errCode);
    }
}
