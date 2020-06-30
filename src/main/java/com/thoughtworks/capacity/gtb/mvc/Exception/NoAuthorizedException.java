package com.thoughtworks.capacity.gtb.mvc.Exception;

public class NoAuthorizedException extends RuntimeException{
    public NoAuthorizedException(String message){
        super(message);
    }
}
