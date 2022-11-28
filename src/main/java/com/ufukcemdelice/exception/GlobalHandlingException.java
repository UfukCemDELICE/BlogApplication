package com.ufukcemdelice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlingException {
    //Spring is going to capture if there will be null value
    @ExceptionHandler({NullPointerException.class})
    public String handlingNullPointerException(){
        return "Null value entered";
    }

    @ExceptionHandler({UfukCemException.class})
    public String handlingNotFoundException(){
        return "There is no such value";
    }
}
