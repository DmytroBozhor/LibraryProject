package com.example.library.controller;

import com.example.library.model.BookEntity;
import com.example.library.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String bookMainPage(){
        return "book/main_page";
    }

    @GetMapping("/new")
    public String bookRegisterGet(){
        return "book/register";
    }

    @PostMapping("/new")
    public String bookRegisterPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));

        bookService.save(new BookEntity(title, author, releaseYear));
        log.info("A new book has been added");

        return "redirect:/books";
    }

    @GetMapping("/edit")
    public String bookEditGet(){
        return "book/edit";
    }

    @PostMapping("/edit")
    public String bookEditPost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));

        bookService.update(id, new BookEntity(title, author, releaseYear));
        log.info("The book's data has been edited");

        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String bookDeleteGet(){
        return "book/delete";
    }

    @PostMapping("/delete")
    public String bookDeletePost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        bookService.deleteById(id);
        log.info("The book has been deleted");

        return "redirect:/books";
    }

}
