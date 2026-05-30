package com.library.library.Controllers;

import com.library.library.DTO.CreateBookDTO;
import com.library.library.DTO.TitleBookDTO;
import com.library.library.Entites.BookEntity;
import com.library.library.Services.BookService;
import com.library.library.Services.LoansService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final LoansService loansService;

    @PostMapping("/addBook")
    public ResponseEntity<BookEntity> createBook(@RequestBody CreateBookDTO book){
        BookEntity createBook = bookService.createBook(book);
        log.info("добавлена новая книга " + createBook );
        return ResponseEntity.status(HttpStatus.CREATED).body(createBook);
    }

    @GetMapping("/searchLike")
    public ResponseEntity<List<BookEntity>> searchBookLike(@RequestParam String text){
        List<BookEntity> listResponseEntity = bookService.searchBookLike(text);
        return ResponseEntity.ok(listResponseEntity);
    }

    @GetMapping("/searchLike/title")
    public ResponseEntity<List<TitleBookDTO>> searchTitleLike(@RequestParam String title){
        List<TitleBookDTO> listTitles = bookService.searchTitleLike(title);
        return ResponseEntity.ok(listTitles);
    }


    @PostMapping("/delete/{id}")
    public ResponseEntity<?> delBook(@PathVariable Long id){
       try {
           bookService.deleteBook(id);
           log.info("книга с индексом {} удалена успешно", id);
           return ResponseEntity.ok().build();
       }catch (BookService.ForeignKeyViolationException e){
           log.info("книга с индексом {} имеет активные выдачи, удаление невозможно", id);
           return ResponseEntity.status(HttpStatus.CONFLICT)
                   .body(Map.of(
                           "error", "CONFLICT",
                           "message", "Книга №" + id + " имеет активные выдачи"));
       }
    }
}
