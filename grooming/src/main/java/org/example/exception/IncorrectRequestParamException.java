package org.example.exception;

public class IncorrectRequestParamException extends RuntimeException{

    public IncorrectRequestParamException(String message) {
        super(message);
    }
}
