package com.BookStore.BookStore.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CategoryName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Book> books;
    public Category (){

    }


    public Category(Long id, String categoryName, Set<Book> books) {
        this.id = id;
        CategoryName = categoryName;
        this.books = books;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }
}
