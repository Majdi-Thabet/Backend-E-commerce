package com.project.Ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	Category findByCategoryName(String categoryName);
}
