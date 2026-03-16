package io.reflectoring.library.books;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookController bookController;

    @Test
    void contextLoads() {
        assertThat(bookController).isNotNull();
        assertThat(bookService).isNotNull();
    }

    @Test
    void testGetBooksReturnsAnswer() throws Exception {
        //given
        BookDto book = new BookDto();
        book.setIsbn("12324");
        book.setName("Britt Marii");
        book.setAuthor("Backman");
        book.setLocation("R2-P101");

        when(bookService.findAll()).thenReturn(List.of(book));

        //when + then
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn").value("12324"))
                .andExpect(jsonPath("$[0].name").value("Britt Marii"))
                .andExpect(jsonPath("$[0].author").value("Backman"))
                .andExpect(jsonPath("$[0].location").value("R2-P101"));

        Mockito.verify(bookService).findAll();
    }

    @Test
    void testPostBookReturnsSuccessCreatedEntity() throws Exception {
        //given
        BookDto request = new BookDto();
        request.setIsbn("12311");
        request.setName("Bear Hole");
        request.setAuthor("Backman");
        request.setLocation("R3-P101");

        BookDto response = new BookDto();
        response.setIsbn("12311");
        response.setName("Bear Hole");
        response.setAuthor("Backman");
        response.setLocation("R3-P101");

        when(bookService.create(any(BookDto.class))).thenReturn(response);

        //when + then
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"isbn\":\"12311\",\"name\":\"Bear Hole\",\"author\":\"Backman\",\"location\":\"R3-P101\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isbn").value("12311"))
                .andExpect(jsonPath("$.name").value("Bear Hole"))
                .andExpect(jsonPath("$.author").value("Backman"))
                .andExpect(jsonPath("$.location").value("R3-P101"));

        verify(bookService).create(any(BookDto.class));

    }
}

