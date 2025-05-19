package com.folmy.folmypractice.exception.conflict;

public class NameAlreadyExistsException extends ConflictException {
    public NameAlreadyExistsException(String message) {
        super(message);
    }
}
