package com.ms.email.interceptor;

import com.ms.email.exceptions.InvalidEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {InvalidEmailException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String deniedPermissionException(InvalidEmailException ex) {
        return "Email Inválido: " + ex.getEmail();
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String constraintViolationException(ConstraintViolationException ex) {
        return "Bad request";
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String internalServerError(Exception ex) {
        return "Internal error";
    }
}