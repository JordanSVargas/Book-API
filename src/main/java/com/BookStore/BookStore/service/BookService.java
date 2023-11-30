package com.BookStore.BookStore.service;

import com.BookStore.BookStore.exceptionHandling.CategoryNotFoundException;
import com.BookStore.BookStore.model.Book;
import com.BookStore.BookStore.model.Category;
import com.BookStore.BookStore.repository.BookRepository;
import com.BookStore.BookStore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    private Category getCategoryById(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id "+id+ " not found."));
    }

    public Iterable<Book> getAllBook(){
        return bookRepository.findAll();
    }
    public Optional<Book> getBookById(Long id){
        return bookRepository.findById(id);
    }
    public Iterable<Book> searchBooks(String keyword){
        return bookRepository.findByNameIgnoreCaseOrSkuContainingIgnoreCase(keyword,keyword);
    }
    public Book createABook(Book book){
        Category category = getCategoryById(book.getCategory().getId());
        book.setCategory(category);
        return bookRepository.save(book);
    }
    public void updateBook(Long bookId,Book book){
        Book originalBook = bookRepository.findById(bookId).get();
        originalBook.setCategory(book.getCategory());
        originalBook.setDescription(book.getDescription());
        originalBook.setName(book.getName());
        originalBook.setSku(book.getSku());
        bookRepository.save(originalBook);
    }
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }



}
