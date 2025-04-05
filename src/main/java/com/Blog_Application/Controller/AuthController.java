package com.Blog_Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog_Application.BlogServices.UserService;
import com.Blog_Application.Entities.User;

import com.Blog_Application.Payload.LoginRequest;
import com.Blog_Application.Payload.UserRequest;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Autowired
    UserService authService;

    @Autowired
    UserRepo UserRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    

    @PostMapping("/register")
    public String register(@RequestBody UserRequest userRequest){

        return authService.RegisterUser(userRequest);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
         authenticationManager.authenticate( 
            new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword())
            );

           User user= UserRepo.findByemail(loginRequest.getEmail()).orElseThrow();
           System.out.println(user+"user in repo");
           return jwtUtil.generateToken(user.getEmail());



        
        
    }


    








}
