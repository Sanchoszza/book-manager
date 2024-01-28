package com.example.bookmanagement.repository;

import com.example.bookmanagement.db.DatabaseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DatabaseCategoryRepository extends JpaRepository<DatabaseCategory, UUID> {
}
