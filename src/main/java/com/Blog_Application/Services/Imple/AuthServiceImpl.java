package com.Blog_Application.Services.Imple;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.Blog_Application.BlogServices.UserService;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Payload.UserRequest;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Security.BCryptPassword;


@Configuration
@Service
public class AuthServiceImpl implements UserService {


    @Autowired
    private BCryptPassword  BCryptPassword;// Inject BCryptPasswordEncoder


    @Autowired
    private UserRepo userRepo;

    
    //@Bean
  //  public BCryptPasswordEncoder passwordEncoder() {
       // return new BCryptPasswordEncoder();
  //  }


  
  
    @Override
    public String RegisterUser(UserRequest userRequest) {
    
        User user=new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(BCryptPassword.passwordEncoder().encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setName(userRequest.getName());
		user.setAbout(userRequest.getAbout());
        userRepo.save(user);
        return "user is successfully register";
    }
    
}
