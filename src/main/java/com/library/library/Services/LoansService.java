package com.library.library.Services;

import com.library.library.DTO.CloseLoansDTO;
import com.library.library.DTO.MakeLoansDTO;
import com.library.library.Entites.BookEntity;
import com.library.library.Entites.BookLoansEntity;
import com.library.library.Entites.ReadersEntity;
import com.library.library.Repositories.BookLoansRepo;
import com.library.library.Repositories.BookRepo;
import com.library.library.Repositories.ReadersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoansService {
    private final BookLoansRepo bookLoansRepo;
    private final BookRepo bookRepo;
    private final ReadersRepo readersRepo;


    @Transactional
    public BookLoansEntity addLoans(MakeLoansDTO request){
        BookEntity book = bookRepo.findById(request.getBookId()).orElseThrow(() -> new RuntimeException("книга не найдена"));
        if (!book.isAvailable()){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "книга уже выдана"
            );
        }

        ReadersEntity reader = readersRepo.findById(request.getReaderId()).orElseThrow(() -> new RuntimeException("пользователь не найден"));

        BookLoansEntity loan = new BookLoansEntity();
        loan.setBook(book);
        loan.setReader(reader);
        loan.setLoan_date(request.getLoanDate());

        book.setAvailable(false);
        bookRepo.save(book);

        return bookLoansRepo.save(loan);
    }
    @Transactional
    public BookLoansEntity closeLoans(CloseLoansDTO request){

        BookLoansEntity loan = bookLoansRepo.getBookLoansEntitiesById(request.getLoanId());
        if (loan.getReturn_date() != null){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "заявка уже была закрыта с датой " + loan.getReturn_date()
            );
        }
        if (request.getReturnDate().isBefore(loan.getLoan_date())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Дата возврата (" + request.getReturnDate() + ") не может быть раньше" +
                    " даты выдачи"
            );
        }
        loan.setReturn_date(request.getReturnDate());
        BookEntity book = loan.getBook();
        book.setAvailable(true);
        log.info("статус is_available книги с индексом {} изменился на true", book.getId());
        bookRepo.save(book);
        log.info("заявка с индексом {} закрыта. дата закрытия {}", loan.getId(), loan.getReturn_date());
        return bookLoansRepo.save(loan);
    }
    // редактировать заявки аренды книг нельзя во избежание их путаницы и для сохранения общей целостности

}
