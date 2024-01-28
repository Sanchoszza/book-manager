package com.example.bookmanagementserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private UUID id;

    private String name;

    private String author;

    private Category category;

    private Instant date;


    public static Book createMockModel(String name, String author, Category category){
        return new Book(UUID.randomUUID(), name, author, category, Instant.now());
    }
}
