package io.reflectoring.library.books.controller;

import io.reflectoring.library.books.BookController;
import io.reflectoring.library.books.BookDto;
import io.reflectoring.library.books.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void getBookByIsbn_returns200AndBookData() throws Exception {
        // given
        BookDto book = new BookDto();
        book.setIsbn("123456");
        book.setName("Test Book");
        book.setAuthor("John Doe");
        book.setLocation("Shelf A1");

        when(bookService.findBookByIsbn("123456")).thenReturn(book);

        // when / then
        mockMvc.perform(get("/books/isbn/123456"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn").value("123456"))
                .andExpect(jsonPath("$.name").value("Test Book"))
                .andExpect(jsonPath("$.author").value("John Doe"))
                .andExpect(jsonPath("$.location").value("Shelf A1"));
    }
}
