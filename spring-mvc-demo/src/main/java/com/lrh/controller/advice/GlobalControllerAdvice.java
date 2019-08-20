package com.lrh.controller.advice;

import com.lrh.controller.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(ValidateException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public Map<String,String> validateExceptionHandle(ValidateException e){
        Map<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return map;
    }
}
