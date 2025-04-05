package com.Blog_Application.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog_Application.BlogServices.commentsServices;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Payload.CommentDto;
import com.Blog_Application.Payload.apiResponse;

@RestController
@RequestMapping("home/api")
public class commentController {
	
	@Autowired
	private commentsServices cServices;
	
	@PostMapping("POST/{id}/comments")
	public ResponseEntity<?> addComment(@RequestBody CommentDto cDto,@PathVariable("id") int id) {
	    return this.cServices.addCommentDto(cDto, id);
		
	}
	
	@PostMapping("POST/comments/{id}/like")
	public apiResponse addLikeComment(@RequestBody User user,@PathVariable("id") int commentId) {
		//CommentDto ans = this.cServices.addCommentDto(cDto, userId);
		//return  new ResponseEntity<>(ans,HttpStatus.CREATED);
		//apiResponse apiResponse=this.cServices.incrementLike(user.getId(),commentId);

		apiResponse apiResponse=this.cServices.incrementLike(user.getId(),commentId);
		return apiResponse;
	}

	@PostMapping("POST/comments/{id}/dislike")
	public apiResponse addDislikeComment(@RequestBody User user,@PathVariable("id") int commentId) {
		apiResponse apiResponse= this.cServices.incrementDisLike(user.getId(), commentId);
		return  apiResponse;
	}
	@DeleteMapping("POST/{id}/comments")
	public apiResponse deleteCommentInController(@PathVariable("id") int id) {
		this.cServices.deleteComment(id);
		apiResponse ar = new apiResponse("comments delete successfully",true);
		return ar;
	}

}
