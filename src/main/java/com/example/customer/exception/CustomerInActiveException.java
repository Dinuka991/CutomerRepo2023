package com.example.customer.exception;

public class CustomerInActiveException extends RuntimeException{
    public CustomerInActiveException(String message){
        super(message);
    }
}
