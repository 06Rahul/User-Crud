package com.example.UserManagement.Service;

import com.example.UserManagement.DTO.RequestBodyDTO;
import com.example.UserManagement.Excepction.EmailAlreadyExistsException;
import com.example.UserManagement.Excepction.UserCreationFailedException;
import com.example.UserManagement.Excepction.UserNotFoundException;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getAllUsers() {
        log.info("Fetching all users from the database.");
        return userRepo.findAll();
    }

    @Transactional
    public void createUser(RequestBodyDTO requestBodyDTO) {
        log.info("Attempting to create a user with email: {}", requestBodyDTO.getEmail());

        if (userRepo.findByEmail(requestBodyDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists: " + requestBodyDTO.getEmail());
        }

        try {
            User newUser = new User();
            newUser.setName(requestBodyDTO.getName());
            newUser.setEmail(requestBodyDTO.getEmail());
            newUser.setAge(requestBodyDTO.getAge());
            userRepo.save(newUser);
            log.info("User created successfully with email: {}", requestBodyDTO.getEmail());
        } catch (Exception ex) {
            log.error("User creation failed for email: {}", requestBodyDTO.getEmail(), ex);
            throw new UserCreationFailedException("User creation failed, please try again.", ex);
        }
    }

    public User findById(Long userId) {
        log.info("Fetching user by ID: {}", userId);
        return userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    public User findByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found with email: " + email));
    }

    @Transactional
    public String updateUserById(Long userId, RequestBodyDTO requestBodyDTO) {
        User user = findById(userId);
        user.setName(requestBodyDTO.getName());
        user.setEmail(requestBodyDTO.getEmail());
        user.setAge(requestBodyDTO.getAge());
        userRepo.save(user);
        log.info("User updated successfully with ID: {}", userId);
        return "User updated successfully with ID: " + userId;
    }

    @Transactional
    public String updateByEmail(String email, RequestBodyDTO requestBodyDTO) {
        User user = findByEmail(email);
        user.setName(requestBodyDTO.getName());
        user.setEmail(requestBodyDTO.getEmail());
        user.setAge(requestBodyDTO.getAge());
        userRepo.save(user);
        log.info("User updated successfully with email: {}", email);
        return "User updated successfully with email: " + email;
    }

    @Transactional
    public String deleteById(Long userId) {
        User user = findById(userId);
        userRepo.deleteById(userId);
        log.info("User deleted successfully with ID: {}", userId);
        return "User deleted successfully.";
    }
}
