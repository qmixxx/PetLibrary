package io.reflectoring.library.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BorrowedRepository
        extends JpaRepository<Borrowed, Long> {

//    @Query("SELECT b FROM Borrowed b WHERE b.isbn = ?1")
    Optional<Borrowed> findBookByIsbn(String isbn);

//    @Query("SELECT b FROM Borrowed b WHERE b.userId = ?1")
    Optional<Borrowed> findByUserId(String userId);

    @Modifying
    @Query("DELETE FROM Borrowed b WHERE b.userId = ?1")
    void deleteByUserId(String userId);
}