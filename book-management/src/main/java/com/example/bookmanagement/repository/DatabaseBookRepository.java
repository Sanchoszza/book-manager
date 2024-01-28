package com.example.bookmanagement.repository;

import com.example.bookmanagement.db.DatabaseBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DatabaseBookRepository extends JpaRepository<DatabaseBook, UUID> {
}
