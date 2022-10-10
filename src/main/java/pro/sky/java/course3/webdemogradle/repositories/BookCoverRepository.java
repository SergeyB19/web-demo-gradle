package pro.sky.java.course3.webdemogradle.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.webdemogradle.model.BookCover;

import java.util.Optional;

public interface BookCoverRepository extends JpaRepository<BookCover, Long> {

    Optional<BookCover> findByBookId(Long bookId);
}
