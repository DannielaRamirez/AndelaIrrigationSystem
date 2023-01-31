package com.agrofarm.irrigationsystem.exception;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ResourceNotConfigureException extends RuntimeException{
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    private final String message;


    public ResourceNotConfigureException(String plotIdentifier, HttpStatus status) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = "Sensor: " +plotIdentifier + " doesn't have configuration";
    }
}
