package com.library.library.Repositories;
import com.library.library.Entites.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<BookEntity, Long>{
    BookEntity getBookEntitiesById(Long id);

    @Query("SELECT b FROM BookEntity b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :text, '%'))")
    List<BookEntity> searchByTitleIgnoreCase(@Param("text") String text);

}
