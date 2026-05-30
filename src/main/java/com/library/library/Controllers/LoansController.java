package com.library.library.Controllers;

import com.library.library.DTO.CloseLoansDTO;
import com.library.library.DTO.MakeLoansDTO;
import com.library.library.Entites.BookLoansEntity;
import com.library.library.Services.LoansService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
public class LoansController {
    private final LoansService loansService;

    @PostMapping("/addLoan")
    public ResponseEntity<BookLoansEntity> addLoans(@RequestBody MakeLoansDTO request){
        BookLoansEntity bookLoansEntity = loansService.addLoans(request);
        log.info("открыта заявка - " + bookLoansEntity);
        return ResponseEntity.status(201).body(bookLoansEntity);
    }

    @PostMapping("/closeLoan")
    public BookLoansEntity closeLoan(@RequestBody CloseLoansDTO request){
        log.info("заявка с id " + request.getReturnDate() + " была закрыта");
        return loansService.closeLoans(request);
    }
}
