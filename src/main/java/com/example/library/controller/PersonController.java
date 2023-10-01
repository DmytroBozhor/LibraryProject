package com.example.library.controller;

import com.example.library.model.PersonEntity;
import com.example.library.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;

@Controller
public class PersonController {
    private final PersonService personService;
    private static final Logger log = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/person")
    public String personMainPage() {
        return "person_main_page";
    }

    @GetMapping("/person/new")
    public String personRegisterGet() {
        return "person_register";
    }

    @PostMapping("/person/new")
    public String personRegisterPost(HttpServletRequest request) {
        String fullName = request.getParameter("fullName");
        Date date = Date.valueOf(request.getParameter("birthday"));

        personService.save(new PersonEntity(fullName, date));
        log.info("New person added to database");

        return "redirect:/";
    }

    @GetMapping("/person/edit")
    public String personEditGet() {
        return "person_edit";
    }

    @PostMapping("/person/edit")
    public String personEditPost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String fullName = request.getParameter("fullName");
        Date date = Date.valueOf(request.getParameter("birthday"));

        personService.update(id, new PersonEntity(fullName, date));
        log.info("The person's data was edited");

        return "redirect:/";
    }

    @GetMapping("/person/delete")
    public String personDeleteGet() {
        return "person_delete";
    }

    @PostMapping("/person/delete")
    public String personDeletePost(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        personService.deleteById(id);
        log.info("The person was deleted");

        return "redirect:/";
    }
}
