package com.example.book_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> updateBook(Integer id, Book book) {
        return bookRepository.findById(id)
                .map(existing -> bookRepository.save(book));
    }

    public boolean deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }
}
