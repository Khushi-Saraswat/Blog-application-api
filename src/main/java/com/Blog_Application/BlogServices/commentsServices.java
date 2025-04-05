package com.Blog_Application.BlogServices;

import org.springframework.http.ResponseEntity;

import com.Blog_Application.Payload.CommentDto;
import com.Blog_Application.Payload.apiResponse;


public interface commentsServices {
	public ResponseEntity<?> addCommentDto(CommentDto cDto,int postId);
	public void deleteComment(int id);
	public apiResponse incrementLike(int userId,int commentId);
	public apiResponse incrementDisLike(int userId,int commentId);
	
	
}
