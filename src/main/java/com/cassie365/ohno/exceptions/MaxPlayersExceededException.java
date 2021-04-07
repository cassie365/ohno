package com.cassie365.ohno.exceptions;

public class MaxPlayersExceededException extends Exception{
    public MaxPlayersExceededException(String msg){
        super(msg);
    }
}
