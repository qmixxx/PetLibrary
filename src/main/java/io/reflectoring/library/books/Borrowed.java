package io.reflectoring.library.books;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Borrowed {

    @Id
    @SequenceGenerator(
            name = "book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )

    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_sequence"
    )

    private Long id;
    private String userId;
    private String isbn;
    private LocalDate due_date;

    public Borrowed() {
    }

    public Borrowed(
            String userId,
            String isbn,
            LocalDate due_date) {
        this.userId = userId;
        this.isbn = isbn;
        this.due_date = due_date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    @Override
    public String toString() {
        return "Borrowed{" +
                "userId='" + userId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", due_date='" + due_date + '\'' +
                '}';
    }

}
