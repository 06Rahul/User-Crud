package com.example.UserManagement.Excepction;

public class EmailAlreadyExistsExcepction extends Exception {
    public EmailAlreadyExistsExcepction(String message) {
        super(message);
    }
}