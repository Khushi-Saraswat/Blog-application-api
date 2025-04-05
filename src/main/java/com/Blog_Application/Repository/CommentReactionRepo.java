package com.Blog_Application.Repository;
import com.Blog_Application.Entities.CommentReaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentReactionRepo extends JpaRepository<CommentReaction, Integer> {
    

        public  Optional<CommentReaction> findByuserIdAndCommentId(int userId, int commentId);






}
