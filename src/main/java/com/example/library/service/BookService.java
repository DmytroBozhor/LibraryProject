package com.example.library.service;

import com.example.library.model.BookEntity;
import com.example.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookEntity getById(int id) {
        return bookRepository.getReferenceById(id);
    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public void save(BookEntity book) {
        bookRepository.save(book);
    }

    public void update(int id, BookEntity book) {
        book.setId(id);
        bookRepository.save(book);
    }

    public void delete(BookEntity book) {
        bookRepository.delete(book);
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }

    public List<BookEntity> getBooksByPersonId(int id){
        return bookRepository.getAllByOwnerId(id);
    }
}
