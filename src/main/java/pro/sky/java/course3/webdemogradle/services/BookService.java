package pro.sky.java.course3.webdemogradle.services;

import org.springframework.stereotype.Service;
import pro.sky.java.course3.webdemogradle.model.Book;
import pro.sky.java.course3.webdemogradle.repositories.BookRepository;

import java.util.Collection;

@Service
public class BookService {


    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBook(long id) {
        return bookRepository.findById(id).get();
    }

    public Book editBook(Book book) {
        return bookRepository.save(book);
    }


    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public Collection<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    public Collection<Book> findByAuthor(String author) {
        return bookRepository.findBookByAuthorContainsIgnoreCase(author);
    }

    public Collection<Book> findByNamePart(String part) {
        return bookRepository.findAllByNameContainsIgnoreCase(part);
    }
}
