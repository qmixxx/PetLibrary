package io.reflectoring.library.books;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@Table
public class Book {

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
    private String isbn;
    private String name;
    private String author;
    private String location;

    public Book() {
    }

    public Book(
            Long id,
            String isbn,
            String name,
            String author,
            String location) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.location = location;
    }

    public Book(String isbn, String name, String author, String location) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Id=" + id +
                ", Isdn='" + isbn + '\'' +
                ", Name='" + name + '\'' +
                ", Author='" + author + '\'' +
                ", Location='" + location + '\'' +
                '}';
    }
}
