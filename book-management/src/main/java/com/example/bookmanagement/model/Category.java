package com.example.bookmanagement.model;

import com.example.bookmanagement.db.DatabaseCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private UUID id;

    private String categoryName;

    public static Category from(DatabaseCategory category){
        return new Category(category.getId(), category.getCategoryName());
    }
}
