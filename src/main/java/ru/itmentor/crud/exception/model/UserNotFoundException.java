package ru.itmentor.crud.exception.model;

public class UserNotFoundException extends RuntimeException {
    private static final String MESSAGE = "User not found";

    public UserNotFoundException() {
        super(MESSAGE);
    }
}
