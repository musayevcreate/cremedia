package com.cremedia.cremedia.exception;


public class IsNotActiveException extends RuntimeException{
    public IsNotActiveException(String message){
        super(message);
    }
}