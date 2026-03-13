package com.example.book_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_api.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
