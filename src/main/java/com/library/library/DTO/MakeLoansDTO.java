package com.library.library.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MakeLoansDTO {
    private Long bookId;
    private Long readerId;
    private LocalDate loanDate;
}
