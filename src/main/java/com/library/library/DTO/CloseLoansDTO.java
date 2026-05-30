package com.library.library.DTO;

import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CloseLoansDTO {
    private Long LoanId;
    private Long bookId;
    private LocalDate returnDate;
}
