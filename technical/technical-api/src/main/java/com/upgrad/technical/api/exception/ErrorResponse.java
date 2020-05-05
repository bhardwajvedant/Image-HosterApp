package com.upgrad.technical.api.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String code;
    private String message;

    public ErrorResponse(String code, String errorMessage, HttpStatus unauthorized) {
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
 
