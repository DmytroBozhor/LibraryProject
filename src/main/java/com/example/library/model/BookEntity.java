package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    PersonEntity owner;

    public BookEntity(String title, String author, int releaseYear) {
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }
}
