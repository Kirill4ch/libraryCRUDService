package com.library.library.Repositories;

import com.library.library.Entites.ReadersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadersRepo extends JpaRepository<ReadersEntity, Long> {
    Optional<ReadersEntity> findReadersEntitiesByName(String name);
}
