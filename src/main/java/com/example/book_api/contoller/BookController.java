package com.example.book_api.contoller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.book_api.model.Book;
import com.example.book_api.service.BookService;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/book")
    @ResponseBody
    public String getBook() {
        // return the title when accessed via /book
        return "도시의 향기";
    }

    @GetMapping("/search")
    public String search(@RequestParam("bookName") String bookName, Model model) {
        // put the parameter into the view model
        model.addAttribute("bookName", bookName);
        // view resolver will look for templates/index.html (Thymeleaf)
        return "index";
    }

    @GetMapping("/books")
    public String books(Model model) {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }
}
