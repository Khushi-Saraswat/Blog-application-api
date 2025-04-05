package com.Blog_Application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Blog_Application.Entities.Category;

public interface categoryRepo extends JpaRepository<Category, Integer> {

   Category findBySlug(String slug);

   List<Category>findByIsDeletedFalse();

}
