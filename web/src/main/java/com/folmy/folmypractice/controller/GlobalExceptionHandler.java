package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.exception.notfound.NotFoundException;
import com.folmy.folmypractice.exception.notfound.ProjectNotFoundException;
import com.folmy.folmypractice.exception.notfound.TeamNotFoundException;
import com.folmy.folmypractice.exception.validation.ValidationException;
import com.folmy.folmypractice.exception.validation.WrongOldPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ProjectNotFoundException.class, TeamNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(WrongOldPasswordException.class)
    public ResponseEntity<String> handleValidation(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
    }
}
