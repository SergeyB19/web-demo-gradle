package pro.sky.java.course3.webdemogradle.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.webdemogradle.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
