package com.library.library.Entites;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
@ToString
@Entity
@Table(name = "books")
@Data
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "published_year")
    private LocalDate publishedYear;
    @Column(name = "is_available")
    private boolean available; // доступна или нет
}
