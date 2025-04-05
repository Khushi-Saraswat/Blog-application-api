package com.Blog_Application.Entities;



import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



import com.Blog_Application.Constant.PostStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int  postId;
	
	
	@Column(name="post_title",length=100,nullable = false)
	private String title;
	
	
	@Column(length = 1000)
	private String content;
	private String imageName;
	private Date uploadDate;
	
    private LocalDateTime scheduledAt;

	@Enumerated(EnumType.STRING)
	private PostStatus status;

	private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

	private int readAt;
   
	    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }




	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> pSet = new HashSet<>();



}
