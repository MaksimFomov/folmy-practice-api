package com.folmy.folmypractice.exception.notfound;

public class TeamNotFoundException extends RuntimeException {
    public TeamNotFoundException() {
        super();
    }

    public TeamNotFoundException(String message) {
        super(message);
    }

    public TeamNotFoundException(Exception e) {
        super(e);
    }

    public TeamNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
