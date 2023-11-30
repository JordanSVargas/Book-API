package com.BookStore.BookStore.controller;

import com.BookStore.BookStore.model.Book;
import com.BookStore.BookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<Iterable<Book>> getAllBooks(){
        Iterable<Book> allBooks = bookService.getAllBook();
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }
    @GetMapping("/books/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable Long book_id){
        Optional<Book> book = bookService.getBookById(book_id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @GetMapping("/search")
    public ResponseEntity<Iterable<Book>> searchBooks(@RequestParam String keyword){
        Iterable<Book> foundBook = bookService.searchBooks(keyword);
        if (!foundBook.iterator().hasNext()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(foundBook, HttpStatus.OK);
    }
    @PostMapping("/books")
    public ResponseEntity<?> createBook(@RequestBody Book book){
        book = bookService.createABook(book);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookUri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(book.getId()).toUri();
        responseHeaders.setLocation(newBookUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }
    @PutMapping("/books/{bookId}")
    public ResponseEntity<?> updateBook(@RequestBody Book book, @PathVariable Long bookId){
        bookService.updateBook(bookId, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook (@PathVariable Long bookId){
        bookService.deleteBookById(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
