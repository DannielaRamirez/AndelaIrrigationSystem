package com.agrofarm.irrigationsystem.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ErrorResponse(String mensaje){
        this.message= mensaje;
    }

    public ErrorResponse(HttpStatus status, String mensaje){
        this.status = status;
        this.message= mensaje;
    }

}
