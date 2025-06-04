package io.reflectoring.library.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/borrowed")
public class BorrowedBooksController {

    private final BorrowedBookService borrowedBookService;

    @Autowired
    public BorrowedBooksController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping
    public List<Borrowed> getBorrowedBooks() {
        return borrowedBookService.getBorrowedBooks();
    }

    @PostMapping
    public void borrowBook(@RequestBody Borrowed borrowed) {
    }

    @DeleteMapping(path = "{userId}")
    public void deleteBookFromBorrowed(@PathVariable("userId") String userId) {
        borrowedBookService.deleteBookFromBorrowed(userId);
    }

    @PutMapping(path = "{userId}")
    public void extendBook(
            @PathVariable("userId") String userId,
            @RequestParam(required = false) String isbn,
            @RequestParam() LocalDate due_date) {
        borrowedBookService.extendBook(userId, isbn, due_date);
    }

}


