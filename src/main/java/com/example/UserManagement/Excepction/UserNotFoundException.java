package com.example.UserManagement.Excepction;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
    }
}
