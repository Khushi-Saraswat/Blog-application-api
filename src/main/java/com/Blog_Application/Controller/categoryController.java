package com.Blog_Application.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Blog_Application.BlogServices.categoryService;
import com.Blog_Application.Payload.CategoryDto;
import com.Blog_Application.Payload.apiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("home/api/category")
public class categoryController {
	
	@Autowired
	private categoryService cService;
	
	@PostMapping("Category")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cDto) {
		CategoryDto newCategory = this.cService.createCategory(cDto);
		return new  ResponseEntity<>(newCategory,HttpStatus.CREATED);
	}
	
	@PutMapping("Category/{slug}")
	public ResponseEntity<CategoryDto> updateCategoryInController(@Valid @RequestBody CategoryDto cDto,@PathVariable("slug") String slug){
		CategoryDto updateDto = this.cService.updateCategory(cDto, slug);
		return new ResponseEntity<>(updateDto,HttpStatus.OK);
	}
	
	@GetMapping("Category/{slug}")
	public ResponseEntity<CategoryDto> getSingleCategoryInController(@PathVariable("slug") String slug){
		CategoryDto category = this.cService.getCategoryBySlug(slug);
		return ResponseEntity.status(HttpStatus.OK).body(category);
	}

	@PutMapping("Category/restore/{slug}")
	public apiResponse RestoreCategory(@PathVariable("slug") String slug){
		this.cService.RestoreCategory(slug);
		apiResponse ar = new apiResponse("deleted categories is restored",true);
		return ar;
		
	}

	
	@GetMapping("Category")
	public ResponseEntity<List<CategoryDto>> getAllCategoryInController(){
		List<CategoryDto> allDto = this.cService.getAllCategory();
		return ResponseEntity.status(HttpStatus.OK).body(allDto);
	}
	
	
	@DeleteMapping("Category/{slug}")
	public apiResponse deleteCategoryInController(@PathVariable("slug") String slug) {
		this.cService.deleteCategory(slug);
		apiResponse ar = new apiResponse("categories deleted successfully",true);
		return ar;
	}

}
