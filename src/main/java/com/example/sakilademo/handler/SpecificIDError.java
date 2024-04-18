package com.example.sakilademo.handler;

import org.springframework.http.HttpStatus;

public class SpecificIDError extends CustomException{
    public SpecificIDError(Short id, HttpStatus httpStatus){
        super(String.format("No such id: %d!", id), httpStatus);
    }
}
