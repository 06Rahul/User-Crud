package com.example.UserManagement.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ResponceBody {

    private Long id;
    private String name;
    private String email;
    private int age;
}
