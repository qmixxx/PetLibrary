package io.reflectoring.library.books;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BookDto findBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(this::mapToDto)
                .orElseThrow(() -> new NotFoundException("Book not found with ISBN: " + isbn));
    }


    public BookDto create(BookDto bookDto) {
        Book book = new Book();
        book.setIsbn(bookDto.getIsbn());
        book.setName(bookDto.getName());
        book.setAuthor(bookDto.getAuthor());
        book.setLocation(bookDto.getLocation());

        book = bookRepository.save(book);
        return mapToDto(book);
    }

    public BookDto updateBookByIsbn(String isbn, BookDto updateBook) {
        Optional<Book> optionalBook = bookRepository.findByIsbnForUpdate(isbn);
        Book book = optionalBook.orElseThrow(() -> new NotFoundException("Book not found with ISBN: " + isbn));

        book.setIsbn(updateBook.getIsbn());
        book.setName(updateBook.getName());
        book.setAuthor(updateBook.getAuthor());
        book.setLocation(updateBook.getLocation());
        book = bookRepository.save(book);
        return mapToDto(book);
    }

    public BookDto deleteBookByIsbn(String isbn) {
        return bookRepository.deleteByIsbn(isbn);
    }

    private BookDto mapToDto(Book book) {
        BookDto dto = new BookDto();
        dto.setIsbn(book.getIsbn());
        dto.setName(book.getName());
        dto.setAuthor(book.getAuthor());
        dto.setLocation(book.getLocation());
        return dto;
    }
}
