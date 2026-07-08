package com.example.knot.exception;

public class NoteConflictException extends RuntimeException {
    public NoteConflictException(String message)
    {
        super(message);
    }
}
