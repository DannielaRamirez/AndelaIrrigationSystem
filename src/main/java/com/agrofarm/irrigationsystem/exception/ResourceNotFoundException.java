package com.agrofarm.irrigationsystem.exception;

public class ResourceNotFoundException extends RuntimeException{
    private String errorCode;
    private String message;

    public ResourceNotFoundException(String message, String errorCode){
        this.errorCode = errorCode;
        this.message = message;
    }

    public ResourceNotFoundException(String message, Throwable cause, String errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }

}
