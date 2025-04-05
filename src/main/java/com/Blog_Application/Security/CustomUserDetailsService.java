package com.Blog_Application.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.Blog_Application.Entities.CustomUserDetails;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Repository.UserRepo;


@Component
public class CustomUserDetailsService  implements UserDetailsService{
    
    
    @Autowired
    UserRepo userRepo;
    
    
    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepo.findByemail(email);

        return user.map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}
