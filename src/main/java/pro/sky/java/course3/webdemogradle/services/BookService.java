package pro.sky.java.course3.webdemogradle.services;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import pro.sky.java.course3.webdemogradle.model.Book;
import pro.sky.java.course3.webdemogradle.repositories.BookCoverRepository;
import pro.sky.java.course3.webdemogradle.repositories.BookRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
//@CacheConfig()
public class BookService {


    private BookRepository bookRepository;

    private final BookCoverRepository bookCoverRepository;

    private final CacheManager cacheManager;

    public BookService(BookRepository bookRepository, BookCoverRepository bookCoverRepository, CacheManager cacheManager) {
        this.bookRepository = bookRepository;
        this.bookCoverRepository = bookCoverRepository;
        this.cacheManager = cacheManager;
    }

    public void cleanCache(String name) {
        cacheManager.getCache(name).clear();
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }



    @Cacheable("books")
    public Book findBook(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }

    @Caching(evict = {
            @CacheEvict("books")
    }, put = {
            @CachePut(key = "#book.id", cacheNames = {"books", "book", "RusLibrary"}, unless = "#result.id>100")
    }
    )
//    @CachePut(value = "books", key = "#book.id", cacheNames = {"books", "book", "RusLibrary"})
    public Book editBook(Book book) {
        return bookRepository.save(book);
    }

    @CacheEvict("books")
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
