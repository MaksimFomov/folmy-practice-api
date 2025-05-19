package com.folmy.folmypractice.exception.validation;

public class WrongOldPasswordException extends ValidationException {
    public WrongOldPasswordException(String message) {
        super(message);
    }
}
