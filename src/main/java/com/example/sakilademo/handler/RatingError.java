package com.example.sakilademo.handler;

import org.springframework.http.HttpStatus;

public class RatingError extends CustomException{
    //Error for rating.
    public RatingError(String rating, HttpStatus httpStatus){
        super(String.format("No such rating called %s!", rating), httpStatus);
    }
}
