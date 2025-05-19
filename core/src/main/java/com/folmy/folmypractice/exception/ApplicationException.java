package com.folmy.folmypractice.exception;

public abstract class ApplicationException extends RuntimeException {
    public ApplicationException(String message) { super(message); }
}
