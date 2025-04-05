package com.Blog_Application.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

    
    Optional<User>findByemail(String email);



}
