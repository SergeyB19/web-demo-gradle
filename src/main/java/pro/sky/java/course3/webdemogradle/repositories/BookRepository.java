package pro.sky.java.course3.webdemogradle.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.java.course3.webdemogradle.model.Book;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByName(String name);

    Collection<Book> findBookByAuthorContainsIgnoreCase(String author);

    Collection<Book> findAllByNameContainsIgnoreCase(String part);

    Collection<Book> findBookByNameIgnoreCaseAndAuthor(String name, String author);

    Collection<Book> findBookByNameOrAuthorAndIdGreaterThan(String name, String author, Long number);


}
