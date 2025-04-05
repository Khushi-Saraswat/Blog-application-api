package com.Blog_Application.Payload;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class postDto {
	
	private String title;
	private String content;
	private String imageName;
	private Date uploadDate;
	private CategoryDto category;
	private UserDto user;
	private Set<CommentDto> comments = new HashSet<>();
	private LocalDateTime scheduledAt;  // Schedule time input
	private int readAt;

}
