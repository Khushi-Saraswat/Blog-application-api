package com.Blog_Application.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Blog_Application.Constant.PostStatus;
import com.Blog_Application.Entities.Category;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Entities.User;

public interface postRepo extends JpaRepository<Post, Integer>{

	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	public List<Post>  findByStatusAndScheduledAtBefore(PostStatus status, LocalDateTime currentTime);


    

	@Query("SELECT p FROM Post p WHERE LOWER(p.content) LIKE LOWER(CONCAT('%', :content, '%'))")
    Post searchPost(String content);

}


