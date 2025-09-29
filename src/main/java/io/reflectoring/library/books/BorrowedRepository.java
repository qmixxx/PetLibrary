package io.reflectoring.library.books;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowedRepository extends JpaRepository<Borrowed, Long> {
    boolean existsByBookIsbn(String isbn);
}
