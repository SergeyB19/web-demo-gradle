package pro.sky.java.course3.webdemogradle;

import org.assertj.core.api.Assertions;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.java.course3.webdemogradle.controllers.BooksController;
import pro.sky.java.course3.webdemogradle.model.Book;
import pro.sky.java.course3.webdemogradle.repositories.BookCoverRepository;
import pro.sky.java.course3.webdemogradle.repositories.BookRepository;
import pro.sky.java.course3.webdemogradle.services.BookCoverService;
import pro.sky.java.course3.webdemogradle.services.BookService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@WebMvcTest
public class WebDemoGradleApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private BookCoverRepository bookCoverRepository;

    @SpyBean
    private BookService bookService;

    @SpyBean
    private BookCoverService bookCoverService;

    @InjectMocks
    private BooksController booksController;

    @Test
    public void saveBookTest() throws Exception {
        final String name = "121232132";
        final String author = "sasdasdas";
        final long id = 1;

        JSONObject bookObject = new JSONObject();
        bookObject.put("name", name);
        bookObject.put("author", author);

        Book book = new Book();
        book.setId(1);
        book.setName("121232132");
        book.setAuthor(author);

        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookRepository.findById(any(Long.class))).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                        .content(bookObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/ + id")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.author").value(author));
    }


//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private BooksController booksController;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Test
//    public void contextLoads() throws Exception {
//        Assertions.assertThat(booksController).isNotNull();
//    }
//
//    @Test
//    public void testDefaultMessage() throws Exception {
//        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/", String.class))
//                .isEqualTo("WepApp os working!");
//    }
//
//    @Test
//    public void testGetBooks() throws Exception {
//        Assertions.assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/books", String.class))
//                .isNotNull();
//    }
//    @Test
//    public void testPostBooks() throws Exception {
//        Book book = new Book();
//        book.setName("sdsds");
//        book.setAuthor("AKJSDASjasfkjsaf");
//
//        Assertions.assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/books", book, String.class))
//                .isNotNull();
//    }
}
