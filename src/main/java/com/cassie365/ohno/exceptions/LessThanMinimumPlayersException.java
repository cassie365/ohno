package com.cassie365.ohno.exceptions;

public class LessThanMinimumPlayersException extends Exception {
    public LessThanMinimumPlayersException(String msg){
        super(msg);
    }
}
