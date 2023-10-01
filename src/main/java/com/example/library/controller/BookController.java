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

@Controller
public class BookController {

    private final BookService bookService;
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public String bookMainPage(){
        return "book_main_page";
    }

    @GetMapping("/book/new")
    public String bookRegisterGet(){
        return "book_register";
    }

    @PostMapping("/book/new")
    public String bookRegisterPost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));

        bookService.save(new BookEntity(title, author, releaseYear));
        log.info("A new book has been added");

        return "redirect:/book";
    }

    @GetMapping("/book/edit")
    public String bookEditGet(){
        return "book_edit";
    }

    @PostMapping("/book/edit")
    public String bookEditPost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));

        bookService.update(id, new BookEntity(title, author, releaseYear));
        log.info("The book's data has been edited");

        return "redirect:/book";
    }

    @GetMapping("/book/delete")
    public String bookDeleteGet(){
        return "book_delete";
    }

    @PostMapping("/book/delete")
    public String bookDeletePost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        bookService.deleteById(id);
        log.info("The book has been deleted");

        return "redirect:/book";
    }

}
