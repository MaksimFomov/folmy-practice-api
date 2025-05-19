package com.folmy.folmypractice.exception.validation;

import com.folmy.folmypractice.exception.ApplicationException;

public class ValidationException extends ApplicationException {
    public ValidationException(String message) {
        super(message);
    }
}
