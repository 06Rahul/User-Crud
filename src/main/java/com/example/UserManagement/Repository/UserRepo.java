package com.example.UserManagement.Repository;

import com.example.UserManagement.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    User findByEmail(String email);
}
