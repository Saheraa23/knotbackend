package com.example.knot.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(Exception e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    
    @ExceptionHandler(StickyNoteNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(Exception e)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    @ExceptionHandler(NoteConflictException.class)
    public ResponseEntity<?> handleConflict(Exception e)
    {

        Map<String, String> fault = new HashMap<>();
        fault.put("message", e.getMessage());
         return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException e)
    {
    Map<String, String> fault = new HashMap<>();
    fault.put("message", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fault);
    }


    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Map<String, String>> handleOptimisticLocking(Exception e) {

    Map<String, String> fault = new HashMap<>();
    fault.put("message", "Conflict: This record has been modified by another user.");

    return ResponseEntity.status(HttpStatus.CONFLICT).body(fault);
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException e)
    {
     Map<String,String> Fault = new HashMap<>();
     e.getBindingResult().getFieldErrors().forEach(error-> {
     Fault.put(error.getField(), error.getDefaultMessage());
     });
     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Fault);
    }


}
