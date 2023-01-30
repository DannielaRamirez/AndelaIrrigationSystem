package com.agrofarm.irrigationsystem.exception;

public class ResourceNotConfigure extends RuntimeException{
    private int errorStatus;
    private String message;

    public ResourceNotConfigure() {
        super();
        this.errorStatus = 421;
        this.message = "Sensor doesn´t have configuration";
    }
}
