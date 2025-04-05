package com.Blog_Application.Scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Blog_Application.Constant.PostStatus;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Repository.postRepo;

import jakarta.transaction.Transactional;

@Component
public class PostScheduler {
    
    @Autowired
    private postRepo postRepo;



    @Transactional
    @Scheduled(fixedRate = 60000)
    public void publishScheduledPosts(){

      List<Post>scheduledJob=postRepo.findByStatusAndScheduledAtBefore(PostStatus.SCHEDULED,LocalDateTime.now());

      for(Post post:scheduledJob){
        post.setStatus(PostStatus.PUBLISHED);
        postRepo.save(post);
        System.out.println("Published scheduled post: "+post.getTitle());
      }

    }
}
