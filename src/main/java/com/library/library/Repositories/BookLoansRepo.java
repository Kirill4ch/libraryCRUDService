package com.library.library.Repositories;

import com.library.library.Entites.BookLoansEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLoansRepo extends JpaRepository<BookLoansEntity, Long> {

    BookLoansEntity getBookLoansEntitiesById(Long loanId);
}
