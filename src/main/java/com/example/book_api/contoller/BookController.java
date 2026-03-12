package com.example.book_api.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BookController {
    
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
}
