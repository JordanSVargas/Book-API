package com.BookStore.BookStore.repository;


import com.BookStore.BookStore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
