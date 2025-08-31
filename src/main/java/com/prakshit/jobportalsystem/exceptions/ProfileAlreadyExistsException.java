package com.prakshit.jobportalsystem.exceptions;

public class ProfileAlreadyExistsException extends RuntimeException{

    public ProfileAlreadyExistsException(String message) {
        super(message);
    }
}
