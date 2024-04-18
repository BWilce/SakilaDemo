package com.example.sakilademo.handler;

import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException{
    //Here we are storing our error message for any error.
    //We are also getting the Error code to display to the user.
    //Instead of the usual ResponseStatusException we are now displaying a custom message.
    private final HttpStatus httpStatus;
    public CustomException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }
    public HttpStatus getHttpStatus(){
        return httpStatus;
    }
}
