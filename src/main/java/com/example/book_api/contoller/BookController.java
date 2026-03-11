package com.example.book_api.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    
    @GetMapping("/book")
    public String getBook() {
        // return the title when accessed via /book
        return "도시의 향기";
    }
}
