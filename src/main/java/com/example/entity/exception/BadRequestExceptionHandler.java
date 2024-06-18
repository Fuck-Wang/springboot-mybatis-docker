package com.example.entity.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class BadRequestExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public Map<Object, Object> handler(BadRequestException ex) {
        int status = ex.getStatus();
        String entity = ex.getEntity();
        String message = ex.getMessage();
        return new LinkedHashMap<>() {{
            put("status", status);
            put("entity", entity);
            put("message", message);
        }};
    }
}
