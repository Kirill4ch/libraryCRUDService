package com.library.library.Services;

import com.library.library.DTO.CreateReaderDTO;
import com.library.library.DTO.NameReaderDTO;
import com.library.library.DTO.ReaderEmailDTO;
import com.library.library.DTO.ReaderPhoneDTO;
import com.library.library.Entites.ReadersEntity;
import com.library.library.Repositories.ReadersRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReaderService {
    private final ReadersRepo readersRepo;

    @Transactional
    public ReadersEntity addReader(CreateReaderDTO reader){
            ReadersEntity readersEntity = new ReadersEntity();
            readersEntity.setEmail(reader.getEmail());
            readersEntity.setName(reader.getName());
            readersEntity.setPhone(reader.getPhone());
            return readersRepo.save(readersEntity);
    }

    public NameReaderDTO getNameReaderById(Long id) throws NoSuchElementException{
        Optional<ReadersEntity> readersEntity = readersRepo.findById(id);
        return new NameReaderDTO(readersEntity.get().getName());
    }

    public ReaderEmailDTO getEmailReaderByName(String name) throws NoSuchElementException {
        Optional<ReadersEntity> readersEntity = readersRepo.findReadersEntitiesByName(name);
        return new ReaderEmailDTO(readersEntity.get().getEmail());
    }
    public ReaderPhoneDTO getPhoneReaderByName(String name) throws NoSuchElementException{
        Optional<ReadersEntity> readersEntity = readersRepo.findReadersEntitiesByName(name);
        return new ReaderPhoneDTO(readersEntity.get().getPhone());
    }
}
