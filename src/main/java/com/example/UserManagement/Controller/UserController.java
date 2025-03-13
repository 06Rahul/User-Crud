package com.example.UserManagement.Controller;

import com.example.UserManagement.DTO.RequestBodyDTO;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-api")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@Valid @RequestBody RequestBodyDTO requestBodyDTO) {
        userService.createUser(requestBodyDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully with email: "
                + requestBodyDTO.getEmail());
    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> findById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }

    @GetMapping("/email/{userEmail}")
    public ResponseEntity<User> findByEmail(@PathVariable String userEmail) {
        return ResponseEntity.ok(userService.findByEmail(userEmail));
    }

    @DeleteMapping("/id/{userId}")
    public ResponseEntity<String> deleteById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.deleteById(userId));
    }

    @DeleteMapping("/email/{email}")
    public ResponseEntity<?>deleteByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.deleteByEmail(email));
    }

    @PutMapping("/update-user/id/{userId}")
    public ResponseEntity<String> updateUserById(
            @PathVariable Long userId,
            @Valid @RequestBody RequestBodyDTO requestBodyDTO) {
        return ResponseEntity.ok(userService.updateUserById(userId, requestBodyDTO));
    }

    @PutMapping("/update-user/email/{email}")
    public ResponseEntity<String> updateByEmail(
            @PathVariable String email,
            @Valid @RequestBody RequestBodyDTO requestBodyDTO) {
        return ResponseEntity.ok(userService.updateByEmail(email, requestBodyDTO));
    }

    @GetMapping("/page/")
    public ResponseEntity<Page<User>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        if (page < 0 || size <= 0) {
            throw new IllegalArgumentException("Page and size must be positive values.");
        }
        Page<User> users = userService.getAllUsers(page, size);
        return ResponseEntity.ok(users);
    }

}

