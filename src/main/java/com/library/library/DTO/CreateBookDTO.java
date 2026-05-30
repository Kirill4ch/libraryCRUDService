package com.library.library.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBookDTO {
    private String title;
    private String author;
    private LocalDate publishedYear;
}
