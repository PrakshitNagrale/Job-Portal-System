package com.prakshit.jobportalsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExceptionResponseDTO {

    private String message;
    private int status;
    LocalDateTime localDateTime;


    public ExceptionResponseDTO(String message, int status, LocalDateTime localDateTime) {
        this.message = message;
        this.status = status;
        this.localDateTime = localDateTime;
    }
}
