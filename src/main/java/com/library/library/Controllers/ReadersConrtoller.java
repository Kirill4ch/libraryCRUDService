package com.library.library.Controllers;

import com.library.library.DTO.CreateReaderDTO;
import com.library.library.DTO.NameReaderDTO;
import com.library.library.DTO.ReaderEmailDTO;
import com.library.library.DTO.ReaderPhoneDTO;
import com.library.library.Entites.ReadersEntity;
import com.library.library.Services.ReaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReadersConrtoller {
    private final ReaderService readerService;


    @PostMapping("/addReader")
    public ResponseEntity<CreateReaderDTO> addReader(@RequestBody CreateReaderDTO reader){
        ReadersEntity readers = readerService.addReader(reader);
        log.info("добавлен новый пользователь " + readers);
        return ResponseEntity.status(201).body(reader);
    }

    @GetMapping("/getNameById")
    public ResponseEntity<?> getName(@RequestParam Long id){
        try {
            NameReaderDTO nameReaderDTO = readerService.getNameReaderById(id);
            log.info("найден пользователь с id " + id + " " + nameReaderDTO);
            return ResponseEntity.status(200).body(nameReaderDTO);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body("пользователь с id " + id + " не найден");
        }
    }

    @GetMapping("/getEmailByName")
    public ResponseEntity<?> getEmailByName(@RequestParam String name){
        try {
            ReaderEmailDTO readerEmailDTO = readerService.getEmailReaderByName(name);
            log.info("найден email пользователя с именем" + name + " - " + readerEmailDTO);
            return ResponseEntity.status(200).body(readerEmailDTO);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body("пользователь с именем" + name + " не найден");
        }

    }

    @GetMapping("/getPhoneByName")
    public ResponseEntity<?> getPhoneByName(@RequestParam String name){
        try {
            ReaderPhoneDTO readerPhoneDTO = readerService.getPhoneReaderByName(name);
            log.info("найден телефон пользователя с именем " + name + " - " + readerPhoneDTO);
            return ResponseEntity.status(200).body(readerPhoneDTO);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(404).body("пользователь с именем " + name + " не найден");
        }
    }


}
