package io.reflectoring.library.books;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookDto> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
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

    private BookDto mapToDto(Book book) {
        return new BookDto(
                book.getIsbn(),
                book.getName(),
                book.getAuthor(),
                book.getLocation()
        );
    }
}
