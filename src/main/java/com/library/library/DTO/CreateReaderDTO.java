package com.library.library.DTO;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class CreateReaderDTO {

    private String name;
    private String email;
    private String phone;

}
