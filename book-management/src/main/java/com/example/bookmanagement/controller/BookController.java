package com.example.bookmanagement.controller;

import com.example.bookmanagement.db.DatabaseBook;
import com.example.bookmanagement.clients.OpenFeignClient;
import com.example.bookmanagement.db.DatabaseCategory;
import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Category;
import com.example.bookmanagement.model.UpsertBookRequest;
import com.example.bookmanagement.service.DatabaseBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final OpenFeignClient client;

    private final DatabaseBookService service;

    @GetMapping
    public ResponseEntity<List<Book>> bookList(){
        return ResponseEntity.ok(service.findAll().stream().map(Book::from).toList());
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Book> findById(@PathVariable UUID id){
        return ResponseEntity.ok(
                Book.from(service.findById(id))
        );
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<Book> bookByName(@PathVariable String name){
        return ResponseEntity.ok(Book.from(service.findByName(name)));
    }

    @GetMapping("/by-author/{author}")
    public ResponseEntity<Book> bookByAuthor(@PathVariable String author){
        return ResponseEntity.ok(Book.from(service.findByAuthor(author)));
    }

    @GetMapping("/by-category/{category}")
    public ResponseEntity<Book> bookByCategory(@PathVariable DatabaseCategory category){
        return ResponseEntity.ok(Book.from(service.findByCategory(category)));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody UpsertBookRequest request){
        var newBook = client.createBook(request);
        var savedBook = service.create(DatabaseBook.from(newBook));
        return ResponseEntity.status(HttpStatus.CREATED).body(Book.from(savedBook));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable UUID id, @RequestBody UpsertBookRequest request){
        var updatedBook = client.updateBook(id, request);
        var updatedDbBook = service.update(id, DatabaseBook.from(updatedBook));
        return ResponseEntity.ok(Book.from(updatedDbBook));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
