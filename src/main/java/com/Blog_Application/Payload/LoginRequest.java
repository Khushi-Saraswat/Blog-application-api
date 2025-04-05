package com.Blog_Application.Payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
