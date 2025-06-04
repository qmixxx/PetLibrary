package io.reflectoring.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/book")
public class BooksController {

    private final BookService bookservice;

    @Autowired
    public BooksController(BookService bookservice) {
        this.bookservice = bookservice;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookservice.getBooks();
    }

    @PostMapping
    public void postNewBook(@RequestBody Book book) {
        bookservice.addNewBook(book);
    }

    @DeleteMapping(path = "{isbn}")
    public void deleteBook(@PathVariable("isbn") String isbn) {
        bookservice.deleteBook(isbn);
    }
}
