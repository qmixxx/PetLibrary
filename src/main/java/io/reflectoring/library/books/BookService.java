package io.reflectoring.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BorrowedRepository borrowedRepository;

    @Autowired
    public BookService(BookRepository bookRepository, BorrowedRepository borrowedRepository) {
        this.bookRepository = bookRepository;
        this.borrowedRepository = borrowedRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {
        Optional<Book> bookOptional = bookRepository
                .findBookByIsbn(book.getIsbn());
        if (bookOptional.isPresent()) {
            throw new IllegalStateException("ISDN is already Exist");
        }
        bookRepository.save(book);
        System.out.println(book);
    }

    public void deleteBook(String isbn) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(isbn);

        if (bookOptional.isEmpty()) {
            throw new IllegalStateException("the book with ISBN: " + isbn + " doesn't exists");
        }
        bookRepository.delete(bookOptional.get());
    }
}
