package com.example.UserManagement.DTO;

import jakarta.validation.constraints.Size;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Data
public class RequestBodyDTO implements Serializable {

    @NotBlank( message = "Name length must be between 3-25" )
    @Size(min = 3 , max = 25)
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    @Min(value = 1, message = "Age must be greater than zero")
    private int age;
}
