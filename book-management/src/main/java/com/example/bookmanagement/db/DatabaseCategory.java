package com.example.bookmanagement.db;

import com.example.bookmanagement.model.Book;
import com.example.bookmanagement.model.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class DatabaseCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String categoryName;

//    @OneToOne(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private DatabaseBook book;
//
//    @Override
//    public String toString() {
//        return "DatabaseCategory{" +
//                "id=" + id +
//                ", categoryName='" + categoryName + '\'' +
//                '}';
//    }

    public static DatabaseCategory from(Category category){
        return new DatabaseCategory(category.getId(), category.getCategoryName());
    }
}
