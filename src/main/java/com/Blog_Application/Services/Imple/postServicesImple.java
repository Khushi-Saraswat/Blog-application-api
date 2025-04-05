package com.Blog_Application.Services.Imple;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Blog_Application.BlogServices.postServices;
import com.Blog_Application.Constant.PostStatus;
import com.Blog_Application.Entities.Category;
import com.Blog_Application.Entities.Post;
import com.Blog_Application.Entities.User;
import com.Blog_Application.Exception.ResourceNotFoundException;
import com.Blog_Application.Payload.postDto;
import com.Blog_Application.Repository.UserRepo;
import com.Blog_Application.Repository.categoryRepo;
import com.Blog_Application.Repository.postRepo;
import com.Blog_Application.utility.PdfGenerator;
import com.Blog_Application.utility.ReadEstimation;


@Service
public class postServicesImple implements postServices {

	@Autowired
	private postRepo pRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private categoryRepo categoryRepo;
	
	@Autowired
	private UserRepo userRepo;

	ReadEstimation readEstimation=new ReadEstimation();
	
	@Override
	public postDto createPost(postDto postdto,int userId,int categoryId) {
		
		
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));

		Post post = DtoToPost(postdto);
		int readTime=readEstimation.ReadTimeEstimation(post.getContent());
		post.setReadAt(readTime);
		post.setImageName("default.png");
		post.setUploadDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post savedPost = pRepo.save(post);
		return this.PostToDto(savedPost);
	}

	@Override
	public postDto updatePost(postDto postdto, int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		int readTime=readEstimation.ReadTimeEstimation(post.getContent());
		post.setReadAt(readTime);
		
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		Post pr = pRepo.save(post);
		return this.PostToDto(pr);
	}

	@Override
	public postDto getByIdPost(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
		return this.PostToDto(post);
	}

	@Override
	public List<postDto> getAllPost() {
		List<Post> allPosts = pRepo.findAll();
		List<postDto> allDtosPosts = allPosts.stream().map(psa-> (PostToDto(psa))).collect(Collectors.toList());
		return allDtosPosts;
	}

	@Override
	public void delete(int id) {
		Post post = pRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		this.pRepo.delete(post);
		
	}

	@Override
	public List<postDto> getPostByCategory(int id) {
		
		Category ct = categoryRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		List<Post> catePosts = pRepo.findByCategory(ct);
		List<postDto> pstDtos = catePosts.stream().map(ps-> (PostToDto(ps))).collect(Collectors.toList());
		return pstDtos;
	}

	@Override
	public List<postDto> getPostByUser(int id) {
		
		User us = userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
		List<Post> userpst = pRepo.findByUser(us);
		List<postDto> userDtos = userpst.stream().map(usp-> (PostToDto(usp))).collect(Collectors.toList());
		return userDtos;
	}
	
	public Post DtoToPost(postDto pd) {
		Post prPost = this.model.map(pd,Post.class);
		return prPost;
	}
	
	public postDto PostToDto(Post post) {
		postDto pDto = model.map(post, postDto.class);
		return pDto;
	}

	@Override
	public void schedulePost(postDto postDto) {
	  Post post=new Post();
	  post.setTitle(postDto.getTitle());
	  post.setContent(postDto.getContent());
	  post.setStatus(PostStatus.SCHEDULED);
	  post.setScheduledAt(postDto.getScheduledAt());
	  this.pRepo.save(post);

		
	}

	@Override
	public postDto SearchByTitle(String content) {
	    Post post=pRepo.searchPost(content);
		postDto pDto = model.map(post, postDto.class);
		return pDto;
	}

	@Override
	public byte[] exportPostAsPdf(int postId)throws Exception{
		
		Post post = pRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
        return PdfGenerator.generatePdf(post.getTitle(), post.getContent());
		
	}

	
}
