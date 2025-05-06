package com.folmy.folmypractice.exception.notfound;

public class ProjectNotFoundException extends RuntimeException {
    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(Exception e) {
        super(e);
    }

    public ProjectNotFoundException(String message, Exception e) {
        super(message, e);
    }
}
