package com.example.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String  customerNotFoundException(CustomerNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(CustomerInActiveException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String customerNotActiveException(CustomerInActiveException e){
        return e.getMessage();
    }


}

