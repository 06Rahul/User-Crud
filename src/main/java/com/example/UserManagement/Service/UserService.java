package com.example.UserManagement.Service;

import com.example.UserManagement.DTO.RequestBody;
import com.example.UserManagement.Excepction.EmailAlreadyExistsExcepction;
import com.example.UserManagement.Excepction.UserCreationFailExcepction;
import com.example.UserManagement.Excepction.UserNotFoundException;
import com.example.UserManagement.Model.User;
import com.example.UserManagement.Repository.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService {

    @Autowired
    private UserRepo repo;

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    public List<User>getAllUsers(){
        log.info("Fetching all users from database..");
        return repo.findAll();
    }

 public void createUser(RequestBody requestBody) throws EmailAlreadyExistsExcepction, UserCreationFailExcepction {
     log.info("Processing to create user with email: " + requestBody.getEmail());
     User user = repo.findByEmail(requestBody.getEmail());
     if (user == null) {
         try {
             log.info("Creating User Object to store to database from RequestBody");
             User newUser = new User();
             newUser.setName(requestBody.getName());
             newUser.setEmail(requestBody.getEmail());
             newUser.setAge(requestBody.getAge());
             log.info("User created successfully with email: " + requestBody.getEmail());
             repo.save(newUser);
         } catch (Exception ex) {
             log.error("User Creation Failed...");
             throw new UserCreationFailExcepction("Something went wrong. Check again: name, age, and email cannot be empty", ex);
         }
     } else {
         throw new EmailAlreadyExistsExcepction("Email already exists with email: " + requestBody.getEmail());
     }
 }
public User findById(Long userId) throws UserNotFoundException {
    log.info("Fetching user with id: " + userId);
    Optional<User> user = repo.findById(userId);
    if (user.isPresent()) {
        return user.get();
    } else {
        throw new UserNotFoundException("User not found with id: " + userId);
    }
}

    public User findByEmail(String email) throws UserNotFoundException {
        log.info("Fetching user with id: " + email;
        User user = repo.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User not found with id: " + email);
        }
    }

    public String updateUserByEmail(String email , RequestBody requestBody)
            throws UserNotFoundException, UserCreationFailExcepction {
        log.info("findign User with Email:"  + email);
        User user = repo.findByEmail(email);
      if(user != null){
         try{
             User newUser = new User();
             newUser.setEmail(requestBody.getEmail());
             newUser.setName(requestBody.getName());
             newUser.setAge(requestBody.getAge());
             repo.save(newUser);
             return  "User updated Successfully with emai :" + requestBody.getEmail();
         }catch (Exception e){
             throw new UserCreationFailExcepction("Something went wrong while updatig user check again" , e);
         }
      } else {
          throw new UserNotFoundException("User not found with id: " + email);
      }

    }
}
