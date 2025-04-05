package com.Blog_Application.Payload;

import com.Blog_Application.Entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentsReactionDto {
    
	private String content;
	
	
	private Post post;

	private int likeCount;

	private int dislikeCount;
}
