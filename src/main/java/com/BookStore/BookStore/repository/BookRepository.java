package com.BookStore.BookStore.repository;

import com.BookStore.BookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Iterable<Book> findByNameIgnoreCaseOrSkuContainingIgnoreCase(String name, String sku);
}
