package io.reflectoring.library.books;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Borrowed {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrowed_seq")
    @SequenceGenerator(name="borrowed_seq", sequenceName="borrowed_id_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_isbn", referencedColumnName = "isbn", nullable = false)
    private Book book;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private LocalDate dueDate;
}
