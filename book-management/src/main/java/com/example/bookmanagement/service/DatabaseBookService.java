package com.example.bookmanagement.service;

import com.example.bookmanagement.db.DatabaseBook;
import com.example.bookmanagement.configuration.properties.AppCacheProperties;
import com.example.bookmanagement.db.DatabaseCategory;
import com.example.bookmanagement.model.Category;
import com.example.bookmanagement.repository.DatabaseBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheManager = "redisCacheManager")
public class DatabaseBookService {

    private final DatabaseBookRepository repository;

    @Cacheable(AppCacheProperties.CacheNames.DATABASE_BOOKS)
    public List<DatabaseBook> findAll(){
        return repository.findAll();
    }

    @Cacheable(cacheNames = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_ID, key = "#id")
    public DatabaseBook findById(UUID id){
        return repository.findById(id).orElseThrow();
    }

    @Cacheable(AppCacheProperties.CacheNames.DATABASE_BOOK_BY_NAME)
    public DatabaseBook findByName(String name){
        DatabaseBook probe = new DatabaseBook();
        probe.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "author", "category", "date");

        Example<DatabaseBook> example = Example.of(probe, matcher);

        return repository.findOne(example).orElseThrow();
    }

    @Cacheable(AppCacheProperties.CacheNames.DATABASE_BOOK_BY_AUTHOR)
    public DatabaseBook findByAuthor(String author){
        DatabaseBook probe = new DatabaseBook();
        probe.setAuthor(author);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "name", "category", "date");

        Example<DatabaseBook> example = Example.of(probe, matcher);

        return repository.findOne(example).orElseThrow();
    }

    @Cacheable(AppCacheProperties.CacheNames.DATABASE_BOOK_BY_CATEGORY)
    public DatabaseBook findByCategory(DatabaseCategory category){
        DatabaseBook probe = new DatabaseBook();
        probe.setCategory(category);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnorePaths("id", "name", "author", "date");

        Example<DatabaseBook> example = Example.of(probe, matcher);

        return repository.findOne(example).orElseThrow();
    }

    @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOKS, allEntries = true)
    public DatabaseBook create(DatabaseBook book){
        DatabaseBook forSave = new DatabaseBook();
        forSave.setName(book.getName());
        forSave.setAuthor(book.getAuthor());
        forSave.setCategory(book.getCategory());
        forSave.setDate(book.getDate());

        return repository.save(forSave);
    }

    @Caching(evict = {
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOKS, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_NAME, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_AUTHOR, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_CATEGORY, allEntries = true)
    })
    public DatabaseBook update(UUID id, DatabaseBook book){
        DatabaseBook bookForUpdate = findById(id);
        bookForUpdate.setName(book.getName());
        bookForUpdate.setAuthor(book.getAuthor());
        bookForUpdate.setCategory(book.getCategory());
        bookForUpdate.setDate(book.getDate());

        return repository.save(bookForUpdate);
    }

    @Caching(evict = {
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOKS, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_NAME, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_AUTHOR, allEntries = true),
            @CacheEvict(value = AppCacheProperties.CacheNames.DATABASE_BOOK_BY_CATEGORY, allEntries = true)
    })
    public void deleteById(UUID id){
        repository.deleteById(id);
    }
}
