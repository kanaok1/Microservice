package ru.itmentor.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.itmentor.crud.dto.exception.ExceptionResponse;
import ru.itmentor.crud.exception.model.UserNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ExceptionResponse handleUserNotFoundException(UserNotFoundException ex) {
        return new ExceptionResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
}
