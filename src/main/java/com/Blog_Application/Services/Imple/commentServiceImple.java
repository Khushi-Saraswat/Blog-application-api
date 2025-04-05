package com.Blog_Application.Services.Imple;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Blog_Application.BlogServices.commentsServices;
import com.Blog_Application.Constant.ReactionType;
import com.Blog_Application.CoreNlp.Commentmoder;
import com.Blog_Application.Entities.Comment;
import com.Blog_Application.Entities.CommentReaction;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.CommentDto;
import com.Blog_Application.Payload.apiResponse;
import com.Blog_Application.Repository.CommentReactionRepo;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Repository.commentsRepo;
import com.Blog_Application.Repository.postRepo;

@Service
public class commentServiceImple implements commentsServices {

	@Autowired
	private commentsRepo cRepo;

	@Autowired
	private CommentReactionRepo commentrepo;

	@Autowired
	private postRepo psPost;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper model;

	private final Commentmoder commentmoder=new Commentmoder();

	public ResponseEntity<?> addCommentDto(CommentDto cDto, int postId) {
		Post post = this.psPost.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
		Comment com = model.map(cDto, Comment.class);
		com.setPost(post);

		String moderateResult=commentmoder.moderateComment(com.getContent());
		System.out.println("ModerateResult" + moderateResult);
       
        if(moderateResult.equals("The comment is fine")){
		 Comment saveComment = cRepo.save(com);
		 CommentDto responseDto =model.map(saveComment, CommentDto.class);
		 return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
		}
		Map<String,String>response=new HashMap<>();
		response.put("message", "Your comment has been flagged for moderation.");
		response.put("reason",moderateResult);

		return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
	}

	public void deleteComment(int id) {
		Comment comment = this.cRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
		this.cRepo.delete(comment);

	}

	@Override
	public apiResponse incrementDisLike(int userId, int commentId) {
		// check whether user is reacted or not...
		CommentReaction commentReaction = new CommentReaction();
		apiResponse apiResponse;
		Comment comment = this.cRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		Optional<CommentReaction> commentReactions = commentrepo.findByuserIdAndCommentId(userId, commentId);
		if (commentReactions.isPresent()) {

			commentReaction = commentReactions.get();
			if (commentReaction.getReactionType() == ReactionType.DISLIKE) {
				apiResponse = new apiResponse("You already disliked this comment", true);
			}

			commentReaction.setReactionType(ReactionType.DISLIKE);
			comment.setDislikeCount(comment.getLikeCount() + 1);
			comment.setLikeCount(comment.getLikeCount() - 1);

			commentrepo.save(commentReaction);
			cRepo.save(comment);
			apiResponse = new apiResponse("Reaction updated to dislike", true);
			// return new ApiResponse("Reaction updated to like", true);
		}

		commentReaction.setUser(
				userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)));

		commentReaction.setComment(comment);

		commentReaction.setReactionType(ReactionType.DISLIKE);

		// comment.setLikeCount(comment.getLikeCount() +1 );
		comment.setDislikeCount(comment.getDislikeCount() + 1);

		commentrepo.save(commentReaction);

		cRepo.save(comment);

		apiResponse = new apiResponse("Comment disliked successfully!", true);

		return apiResponse;

	}

	@Override
	public apiResponse incrementLike(int userId, int commentId) {
		// check whether user is reacted or not...
		CommentReaction commentReaction = new CommentReaction();
		apiResponse apiResponse;
		Comment comment = this.cRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "id", commentId));
		Optional<CommentReaction> commentReactions = commentrepo.findByuserIdAndCommentId(userId, commentId);
		if (commentReactions.isPresent()) {

			commentReaction = commentReactions.get();
			if (commentReaction.getReactionType() == ReactionType.LIKE) {
				apiResponse = new apiResponse("You already liked this comment", true);
			}

			commentReaction.setReactionType(ReactionType.LIKE);
			comment.setDislikeCount(comment.getDislikeCount() - 1);
			comment.setLikeCount(comment.getLikeCount() + 1);

			commentrepo.save(commentReaction);
			cRepo.save(comment);
			apiResponse = new apiResponse("Reaction updated to like", true);
			// return new ApiResponse("Reaction updated to like", true);
		}

		commentReaction.setUser(
				userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId)));

		commentReaction.setComment(comment);

		commentReaction.setReactionType(ReactionType.LIKE);

		comment.setLikeCount(comment.getLikeCount() + 1);

		commentrepo.save(commentReaction);

		cRepo.save(comment);

		apiResponse = new apiResponse("Comment liked successfully!", true);

		return apiResponse;

	}

}
