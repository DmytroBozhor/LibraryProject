package com.example.library.service;

import com.example.library.model.PersonEntity;
import com.example.library.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonEntity getById(int id) {
        return personRepository.getReferenceById(id);
    }

    public List<PersonEntity> findAll() {
        return personRepository.findAll();
    }

    public void save(PersonEntity person) {
        personRepository.save(person);
    }

    public void update(int id, PersonEntity person) {
        person.setId(id);
        personRepository.save(person);
    }

    public void delete(PersonEntity person) {
        personRepository.delete(person);
    }
}
