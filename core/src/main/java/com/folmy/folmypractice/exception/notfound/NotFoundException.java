package com.folmy.folmypractice.exception.notfound;

import com.folmy.folmypractice.exception.ApplicationException;

public class NotFoundException extends ApplicationException {
    public NotFoundException(String message) { super(message); }
}
