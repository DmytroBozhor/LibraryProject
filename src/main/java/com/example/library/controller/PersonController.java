package com.example.library.controller;

import com.example.library.model.PersonEntity;
import com.example.library.service.BookService;
import com.example.library.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Date;

@Controller
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;
    private final BookService bookService;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService, BookService bookService) {
        this.personService = personService;
        this.bookService = bookService;
    }

    @GetMapping
    public String personMainPage(Model model) {
        model.addAttribute("people", personService.findAll());
        return "person/main_page";
    }

    @GetMapping("/{id}")
    public String getPersonPageById(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.getById(id));
        model.addAttribute("books", bookService.getBooksByPersonId(id));
        return "person/page_by_id";
    }

    @GetMapping("/new")
    public String personRegisterGet() {
        return "person/register";
    }

    @PostMapping("/new")
    public String personRegisterPost(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        Date date = Date.valueOf(request.getParameter("birthday"));

        personService.save(new PersonEntity(fullName, date));
        log.info("New person added to database");

        return "redirect:/people";
    }

    @GetMapping("/edit")
    public String personEditGet() {
        return "person/edit";
    }

    @PostMapping("/edit")
    public String personEditPost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        Date date = Date.valueOf(request.getParameter("birthday"));

        personService.update(id, new PersonEntity(fullName, date));
        log.info("The person's data was edited");

        return "redirect:/people";
    }

    @GetMapping("/delete")
    public String personDeleteGet() {
        return "person/delete";
    }

    @PostMapping("/delete")
    public String personDeletePost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        personService.deleteById(id);
        log.info("The person was deleted");

        return "redirect:/people";
    }
}
