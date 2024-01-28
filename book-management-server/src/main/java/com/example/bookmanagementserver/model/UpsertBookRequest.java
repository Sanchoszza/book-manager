package com.example.bookmanagementserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertBookRequest {

    private String name;

    private String author;

    private Category category;
}
