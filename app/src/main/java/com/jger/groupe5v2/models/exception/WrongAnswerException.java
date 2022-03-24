package com.jger.groupe5v2.models.exception;

public class WrongAnswerException extends Exception{
    public WrongAnswerException(String message){
        super(message);
    }
    public WrongAnswerException(String message, Throwable innerException){
        super(message, innerException);
    }
}
