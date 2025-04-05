package com.Blog_Application.BlogServices;

import java.util.List;
import com.Blog_Application.Payload.CategoryDto;

public interface categoryService {
	
	public CategoryDto createCategory(CategoryDto categorydto);
	public CategoryDto updateCategory(CategoryDto categoryDto ,String slugName);
	public CategoryDto getCategoryBySlug(String slugName);
	public List<CategoryDto> getAllCategory();
	public void deleteCategory(String slugName);
	public void RestoreCategory(String slugName);

}
