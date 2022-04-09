package com.sdoran.fetchrewardschallenge.exception;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<Object> handleHeaderException(final Exception ex) {

        ErrorResponse error = new ErrorResponse("Bad Request", Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(final Exception ex) {

        ErrorResponse error = new ErrorResponse("Server Error", Collections.singletonList(ex.getLocalizedMessage()));
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
