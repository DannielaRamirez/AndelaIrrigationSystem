package com.agrofarm.irrigationsystem.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private final HttpStatus status;
    private final LocalDateTime timestamp;
    private final String message;


    public ResourceNotFoundException(Long id, HttpStatus status) {
        timestamp = LocalDateTime.now();
        this.status = status;
        this.message = "Resource with id: " +id + " is not exist in database";
    }

}
