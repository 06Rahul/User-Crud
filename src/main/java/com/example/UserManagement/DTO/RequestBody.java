package com.example.UserManagement.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RequestBody {

    private String name;
    private String email;
    private int age;

}
