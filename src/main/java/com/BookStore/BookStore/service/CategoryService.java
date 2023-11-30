package com.BookStore.BookStore.service;

import com.BookStore.BookStore.exceptionHandling.CategoryNotFoundException;
import com.BookStore.BookStore.model.Category;
import com.BookStore.BookStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Optional<Category> getCategoryById(Long id){
        return Optional.ofNullable(categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id " + id + " not found.")));
    }

    public Iterable<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
    public Category createCategory( Category category){
        return categoryRepository.save(category);
    }
    public  void updateCategory(Long categoryId, Category category){
        Category originalCategory = categoryRepository.findById(categoryId).get();
        originalCategory.setCategoryName(category.getCategoryName());
        categoryRepository.save(originalCategory);
    }
    public void deleteCategoryById(Long categoryId){
        categoryRepository.deleteById(categoryId);
    }


}
