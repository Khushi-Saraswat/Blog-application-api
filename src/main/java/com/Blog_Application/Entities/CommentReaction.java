package com.Blog_Application.Entities;

import com.Blog_Application.Constant.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="comment_reaction")
public class CommentReaction {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Comment comment;

    @Enumerated(EnumType.STRING) // Store reaction type as string (LIKE/DISLIKE)
    private ReactionType reactionType;
}
