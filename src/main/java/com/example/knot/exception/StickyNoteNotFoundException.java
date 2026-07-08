package com.example.knot.exception;

public class StickyNoteNotFoundException extends RuntimeException{
    public StickyNoteNotFoundException(String text)
    {
        super(text);
    }
}
