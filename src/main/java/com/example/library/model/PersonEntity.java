package com.example.library.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
public class PersonEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;

    @OneToMany(mappedBy = "owner")
    List<BookEntity> books;

    public PersonEntity(String fullName, Date birthday) {
        this.fullName = fullName;
        this.birthday = birthday;
    }
}
