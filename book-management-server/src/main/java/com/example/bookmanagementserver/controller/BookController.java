package com.example.bookmanagementserver.controller;

import com.example.bookmanagementserver.model.Book;
import com.example.bookmanagementserver.model.Category;
import com.example.bookmanagementserver.model.UpsertBookRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/book")
@Slf4j
public class BookController {

    @GetMapping
    public ResponseEntity<List<Book>> bookList(){
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            books.add(Book.createMockModel("Book: " + (i + 1),
                    "\n Author: " + (i + 1), Category.createCategory("\n Category: " + (i + 1))));
        }

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{name}")
    public ResponseEntity<Book> bookByName(@PathVariable String name,
                                             @PathVariable String author, @PathVariable Category category){
        return ResponseEntity.ok(Book.createMockModel(name, author, category));
    }

    @GetMapping("/{author}")
    public ResponseEntity<Book> bookByAuthor(@PathVariable String name,
                                           @PathVariable String author, @PathVariable Category category){
        return ResponseEntity.ok(Book.createMockModel(name, author, category));
    }

    @GetMapping("/{category}")
    public ResponseEntity<Book> bookByCategory(@PathVariable String name,
                                           @PathVariable String author, @PathVariable Category category){
        return ResponseEntity.ok(Book.createMockModel(name, author, category));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody UpsertBookRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                Book.createMockModel(request.getName(), request.getAuthor(), request.getCategory()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody UpsertBookRequest request){
        return  ResponseEntity.ok(new Book(id, request.getName(), request.getAuthor(),
                request.getCategory(), Instant.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBookById(@PathVariable UUID id){
        log.info("Delete entity by id {}", id);

        return ResponseEntity.noContent().build();
    }
}
