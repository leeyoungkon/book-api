package com.example.book_api.contoller;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.book_api.model.Book;
import com.example.book_api.service.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/book")
    public String getBookByParam(@RequestParam("id") Integer id, Model model) {
        return getBook(id, model);
    }

    @GetMapping("/book/{id}")
    public String getBook(@PathVariable("id") Integer id, Model model) {
        try {
            Book book = bookService.findBookById(id);
            model.addAttribute("books", List.of(book));
            model.addAttribute("searchId", id);
            model.addAttribute("message", null);
        } catch (ResponseStatusException ex) {
            model.addAttribute("books", Collections.emptyList());
            model.addAttribute("searchId", id);
            model.addAttribute("message", "검색한 ID의 도서를 찾을 수 없습니다.");
        }
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam("id") Integer id, Model model) {
        return getBook(id, model);
    }

    @GetMapping({"/", "/books"})
    public String books(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }
}
