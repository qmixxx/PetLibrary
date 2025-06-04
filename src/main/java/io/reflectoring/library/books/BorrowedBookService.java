package io.reflectoring.library.books;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class BorrowedBookService {
    private final BorrowedRepository borrowedRepository;

    @Autowired
    public BorrowedBookService(BorrowedRepository borrowedRepository) {
        this.borrowedRepository = borrowedRepository;
    }

    public List<Borrowed> getBorrowedBooks() {
        return borrowedRepository.findAll();
    }

    public void extendBook(String userId, String isbn, LocalDate due_date) {
        Borrowed borrowed = borrowedRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalStateException(
                        ("No book belong to " + userId + " user")
                ));
        if (isbn != null && !isbn.isEmpty()) {
            borrowed.setDue_date(due_date);
        }
    }

    public void deleteBookFromBorrowed(String userId) {
        Optional<Borrowed> exist = borrowedRepository.findByUserId(userId);

        if (exist.isEmpty()) {
            throw new IllegalStateException("User with id " + userId + " does not exist");
        }

        borrowedRepository.deleteByUserId(userId); // Исправленный метод удаления
    }

}
