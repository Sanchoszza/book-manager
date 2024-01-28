package com.example.bookmanagement.clients;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.UpsertBookRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "OpenFeignClient", url = "${app.bookmanager.base-url}")
public interface OpenFeignClient {

    @GetMapping(value = "/api/v1/book")
    List<Book> getBookList();

    @GetMapping(value = "/api/v1/entity/{name}")
    Book getBookByName(@PathVariable("name") String name);

    @PostMapping(value = "/api/v1/book")
    Book createBook(UpsertBookRequest request);

    @PutMapping(value = "/api/v1/book/{id}")
    Book updateBook(@PathVariable("id")UUID id, UpsertBookRequest request);

    @DeleteMapping(value = "/api/v1/book/{id}")
    void deleteBookById(@PathVariable("id") UUID id);
}
