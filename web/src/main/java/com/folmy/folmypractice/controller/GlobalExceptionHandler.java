package com.folmy.folmypractice.controller;

import com.folmy.folmypractice.exception.conflict.ConflictException;
import com.folmy.folmypractice.exception.conflict.TeamIsTiedException;
import com.folmy.folmypractice.exception.notfound.NotFoundException;
import com.folmy.folmypractice.exception.notfound.ProjectNotFoundException;
import com.folmy.folmypractice.exception.notfound.TeamNotFoundException;
import com.folmy.folmypractice.exception.notfound.UserNotFoundException;
import com.folmy.folmypractice.exception.validation.EmptyMemberSet;
import com.folmy.folmypractice.exception.validation.ValidationException;
import com.folmy.folmypractice.exception.validation.WrongOldPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.NameAlreadyBoundException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler({NameAlreadyBoundException.class, TeamIsTiedException.class})
    public ResponseEntity<String> handleNotFoundException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler({ProjectNotFoundException.class, TeamNotFoundException.class, UserNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler({WrongOldPasswordException.class, EmptyMemberSet.class})
    public ResponseEntity<String> handleValidation(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex);
    }
}
