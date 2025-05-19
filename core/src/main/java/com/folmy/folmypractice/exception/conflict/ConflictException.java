package com.folmy.folmypractice.exception.conflict;

import com.folmy.folmypractice.exception.ApplicationException;

public class ConflictException extends ApplicationException {
    public ConflictException(String message) {
        super(message);
    }
}
