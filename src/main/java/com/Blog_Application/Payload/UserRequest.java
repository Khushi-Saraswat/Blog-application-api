package com.Blog_Application.Payload;

import com.Blog_Application.Constant.Role;

import lombok.Data;

@Data
public class UserRequest {
    private Role role;
	private String email;
	private String password;
	private String name;
	private String about;
}
