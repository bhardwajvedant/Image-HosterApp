package com.upgrad.technical.api.exception;

import com.upgrad.technical.service.exception.*;

import javax.persistence.NoResultException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<AuthenticationFailedException> authenticationFailedException(AuthenticationFailedException exc, WebRequest request) {
        return new ResponseEntity<AuthenticationFailedException>(
                exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UploadFailedException.class)
    public ResponseEntity<UploadFailedException> uploadFailedException(UploadFailedException exc, WebRequest request) {
        return new ResponseEntity<UploadFailedException>(
                exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ImageNotFoundException> imagenotfoundException(ImageNotFoundException exc, WebRequest request) {
        return new ResponseEntity<ImageNotFoundException>(
                exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserNotSignedInException.class)
    public ResponseEntity<UserNotSignedInException> usernotsignedinException(UserNotSignedInException exc, WebRequest request) {
        return new ResponseEntity<UserNotSignedInException>(
                exc, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedException> unauthorizedException(UnauthorizedException exc, WebRequest request) {
        return new ResponseEntity<UnauthorizedException>(
                exc, HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<NoResultException> noresultException(NoResultException exc, WebRequest request) {
    	
    	return new ResponseEntity<NoResultException>(
                exc, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}