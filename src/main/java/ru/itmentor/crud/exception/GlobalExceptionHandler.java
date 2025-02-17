package ru.itmentor.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itmentor.crud.dto.exception.ExceptionResponseDTO;
import ru.itmentor.crud.exception.model.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponseDTO handleUserNotFoundException(UserNotFoundException ex) {
        return new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
