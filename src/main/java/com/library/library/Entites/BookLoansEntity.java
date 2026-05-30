package com.library.library.Entites;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "book_loans")
@Data
public class BookLoansEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "book_id")
    private BookEntity book;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private ReadersEntity reader;

    private LocalDate loan_date;

    private LocalDate return_date;
}
