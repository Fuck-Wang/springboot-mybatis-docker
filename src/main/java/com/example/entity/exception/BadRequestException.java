package com.example.entity.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadRequestException extends RuntimeException{

    private int status;

    private String entity;

    private String message;

    public BadRequestException() {}

    public BadRequestException(int status, String entity, String message) {
        this.status = status;
        this.entity = entity;
        this.message = message;
    }

}
