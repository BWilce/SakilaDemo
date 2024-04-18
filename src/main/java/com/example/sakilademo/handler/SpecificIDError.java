package com.example.sakilademo.handler;

import org.springframework.http.HttpStatus;

public class SpecificIDError extends CustomException{
    //Error for the wrong ID.
    public SpecificIDError(Short id, HttpStatus httpStatus){
        super(String.format("No such id: %d!", id), httpStatus);
    }
}
