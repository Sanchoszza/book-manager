package com.example.bookmanagementserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    private UUID id;

    private String categoryName;

    public static Category createCategory(String categoryName){
        return new Category(UUID.randomUUID(), categoryName);
    }
}
