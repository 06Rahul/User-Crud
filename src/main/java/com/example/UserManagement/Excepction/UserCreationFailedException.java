package com.example.UserManagement.Excepction;

public class UserCreationFailedException extends RuntimeException {
    public UserCreationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
