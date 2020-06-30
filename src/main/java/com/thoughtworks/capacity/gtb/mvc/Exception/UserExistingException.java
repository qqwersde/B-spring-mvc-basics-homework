package com.thoughtworks.capacity.gtb.mvc.Exception;


public class UserExistingException extends RuntimeException{

    public UserExistingException(String message){
        super(message);
    }
}
