package com.Blog_Application.BlogServices;

import java.util.List;


import com.Blog_Application.Payload.postDto;

public interface postServices {

	public postDto createPost(postDto postdto,int userId,int categoryId);
	
	public postDto updatePost(postDto postdto,int id);
	
	public postDto getByIdPost(int id);
	
	public List<postDto> getAllPost();
	
	public void delete(int id);
	
	public List<postDto> getPostByCategory(int id);
	
	public List<postDto> getPostByUser(int id);

	public void schedulePost(postDto postDto);

	public postDto SearchByTitle(String content);

	public byte[] exportPostAsPdf(int postId) throws Exception;
	

}
