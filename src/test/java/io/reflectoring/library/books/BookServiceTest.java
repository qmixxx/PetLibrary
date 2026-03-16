package io.reflectoring.library.books;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    void findAll_ReturnsMappedDtoList() {
        //given
        Book book = new Book();
        book.setIsbn("12324");
        book.setName("Britt Marii");
        book.setAuthor("Backman");
        book.setLocation("R2-P101");

        when(bookRepository.findAll()).thenReturn(List.of(book));

        List<BookDto> result = bookService.findAll();

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getIsbn()).isEqualTo("12324");
        assertThat(result.get(0).getName()).isEqualTo("Britt Marii");
    }

    @Test
    void findBookByIsbn_returnsBook_whenExists() {

        String isbn = "12345";
        // given
        Book book = new Book();
        book.setIsbn("12345");
        book.setName("Clean Code");
        book.setAuthor("Robert C. Martin");
        book.setLocation("Shelf 1");

        when(bookRepository.findByIsbn("12345")).thenReturn(Optional.of(book));
        log.info("Mock setup: return {} when findByIsbn({})", book, isbn);

        // when
        BookDto result = bookService.findBookByIsbn("12345");
        verify(bookRepository).findByIsbn(isbn);

        log.info("✅ Verified call to bookRepository.findByIsbn('{}')", isbn);

        // then
        assertThat(result).isNotNull();
        assertThat(result.getIsbn()).isEqualTo("12345");
        assertThat(result.getName()).isEqualTo("Clean Code");
        assertThat(result.getAuthor()).isEqualTo("Robert C. Martin");
    }

    @Test
    void findBookByIsbn_throwsException_whenNotFound() {
        // given
        when(bookRepository.findByIsbn("999")).thenReturn(Optional.empty());

        // when + then
        assertThrows(NotFoundException.class, () -> bookService.findBookByIsbn("999"));
    }

    @Test
    void createBook_SavesAndReturnsDto() throws InstantiationException, IllegalAccessException {
        //given
        BookDto request = new BookDto();
        request.setIsbn("12311");
        request.setName("Bear Hole");
        request.setAuthor("Backman");
        request.setLocation("R3-P101");

        BookDto entity = new BookDto();
        entity.setIsbn("12311");
        entity.setName("Bear Hole");
        entity.setAuthor("Backman");
        entity.setLocation("R3-P101");

        when(bookRepository.save(BookDto.class(entity)), 

        // when
        BookDto result = bookService.create(request);

        log.info("✅ Verified call to bookRepository.save('{}')", result);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getIsbn()).isEqualTo("12311");
        assertThat(result.getName()).isEqualTo("Bear Hole");
        assertThat(result.getAuthor()).isEqualTo("Backman");
        assertThat(result.getLocation()).isEqualTo("R3-P101");
        verify(bookRepository).save(any(Book.class));
    }

}
