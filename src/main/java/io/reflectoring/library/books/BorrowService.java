package io.reflectoring.library.books;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowedRepository borrowedRepo;
    private final BookRepository bookRepo;

    @Transactional
    public BorrowedDto borrow(String isbn, String userId) {
        // Находим книгу
        Book book = bookRepo.findByIsbn(isbn)
                .orElseThrow(() -> new NotFoundException("Book not found"));

        // Проверяем, не занята ли книга уже
        if (borrowedRepo.existsByBookIsbn(isbn)) {
            throw new ConflictException("Book already borrowed");
        }

        // Создаём запись borrow
        Borrowed borrowed = new Borrowed();
        borrowed.setBook(book);
        borrowed.setUserId(userId);
        borrowed.setDueDate(LocalDate.now().plusWeeks(4));

        try {
            borrowed = borrowedRepo.save(borrowed);
        } catch (DataIntegrityViolationException ex) {
            throw new ConflictException("Book already borrowed");
        }

        return mapToDto(borrowed);
    }

    @Transactional
    public BorrowedDto extendDueDate(String isbn, String userId) throws ChangeSetPersister.NotFoundException {
        Borrowed borrowed = borrowedRepo.findAll().stream()
                .filter(b -> b.getBook().getIsbn().equals(isbn) && b.getUserId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Borrowed book not found"));

        borrowed.setDueDate(borrowed.getDueDate().plusWeeks(4));
        borrowed = borrowedRepo.save(borrowed);

        return mapToDto(borrowed);
    }

    private BorrowedDto mapToDto(Borrowed b) {
        return new BorrowedDto(b.getBook().getIsbn(), b.getUserId(), b.getDueDate());
    }
}
