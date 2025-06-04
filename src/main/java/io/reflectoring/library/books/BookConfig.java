package io.reflectoring.library.books;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class BookConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            BookRepository repository) {
        return args -> {
            Book brittMari = new Book(
                    "12324",
                    "Britt Marii",
                    "Backman",
                    "R2-P101"
            );

            Book uwe = new Book(
                    "12325",
                    "UweB",
                    "Backman",
                    "R2-P102"
            );

            repository.saveAll(
                    List.of(brittMari, uwe)
            );
        };
        }
}
