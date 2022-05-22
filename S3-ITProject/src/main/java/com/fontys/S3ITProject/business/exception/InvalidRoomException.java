package com.fontys.s3itproject.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidRoomException extends ResponseStatusException {
    public InvalidRoomException(String errCode){
        super(HttpStatus.BAD_REQUEST, errCode);
    }
}
