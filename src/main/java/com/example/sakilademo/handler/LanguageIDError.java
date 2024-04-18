package com.example.sakilademo.handler;

import org.springframework.http.HttpStatus;

public class LanguageIDError extends CustomException{
    public LanguageIDError(byte languageID, HttpStatus httpStatus){
        super(String.format("No such languageID available for %d!", languageID), httpStatus);
    }

}
