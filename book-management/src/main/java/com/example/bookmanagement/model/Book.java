package com.example.bookmanagement.model;

import com.example.bookmanagement.db.DatabaseBook;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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

    public static Book from(DatabaseBook book){
        return new Book(book.getId(), book.getName(),
                book.getAuthor(), Category.from(book.getCategory()), book.getDate());
    }
}
