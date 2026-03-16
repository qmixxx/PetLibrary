package io.reflectoring.library.books;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookService bookService;

    @GetMapping
    public List<BookDto> getAllBooks() {
        log.info(" Received request: getAllBooks()");
        List<BookDto> books = bookService.findAll();
        log.info("Returning {} books", books.size());
        return books;
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getBookByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        BookDto created = bookService.create(bookDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/isbn/{isbn}")
                .buildAndExpand(created.getIsbn())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

}
