package com.library.library.Services;

import com.library.library.DTO.CreateBookDTO;
import com.library.library.DTO.TitleBookDTO;
import com.library.library.Entites.BookEntity;
import com.library.library.Repositories.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public BookEntity createBook(CreateBookDTO book){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(book.getAuthor());
        bookEntity.setTitle(book.getTitle());
        bookEntity.setPublishedYear(book.getPublishedYear());
        bookEntity.setAvailable(true);
        return bookRepo.save(bookEntity);
    }

    public void deleteBook(Long id){
        try {
            bookRepo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ForeignKeyViolationException("книга №" + id + " имеет активные выдачи");
        }
    }

    public List<BookEntity> searchBookLike(String text){
        List<BookEntity> book = bookRepo.searchByTitleIgnoreCase(text);
        return book;
    }

    public List<TitleBookDTO> searchTitleLike(String text){
        List<BookEntity> bookEntityList = bookRepo.searchByTitleIgnoreCase(text);
        return bookEntityList.stream()
                .map(book -> new TitleBookDTO(book.getTitle())).collect(Collectors.toList());
    }

    public BookEntity redactBook(BookEntity bookEntity){
        try {
            BookEntity book = bookRepo.getBookEntitiesById(bookEntity.getId());
            book.setTitle(bookEntity.getTitle());
            book.setAuthor(bookEntity.getAuthor());
            book.setAvailable(bookEntity.isAvailable());
            book.setPublishedYear(bookEntity.getPublishedYear());
            return bookRepo.save(book);
        }catch (DataIntegrityViolationException e){
            throw new ForeignKeyViolationException("книга №" + bookEntity.getId() + " имеет активные выдачи");
        }
    }
    public class ForeignKeyViolationException extends RuntimeException {
        public ForeignKeyViolationException(String message) {
            super(message);
        }

    }

}
