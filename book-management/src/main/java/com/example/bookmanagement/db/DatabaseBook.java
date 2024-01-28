package com.example.bookmanagement.db;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class DatabaseBook implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String author;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private DatabaseCategory category;

    private Instant date;

    @Override
    public String toString() {
        return "DatabaseBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", date=" + date +
                '}';
    }

    public static DatabaseBook from(Book book){
        return new DatabaseBook(book.getId(), book.getName(),
                book.getAuthor(), DatabaseCategory.from(book.getCategory()), book.getDate());
    }
}
