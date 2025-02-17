package ru.itmentor.crud.dto.exception;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ExceptionResponseDTO {
    private final String message;
    private final Integer code;
    private final Timestamp timestamp;

    public ExceptionResponseDTO(String message, Integer code) {
        this.message = message;
        this.code = code;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }
}
