package io.reflectoring.library.books;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class BorrowedBookConfig {

    @Bean
    CommandLineRunner borrowedBookRunner(
            BorrowedRepository repository) {
        return args -> {
            Borrowed brittMari = new Borrowed(
                    "111",
                    "12324",
                    LocalDate.of(2000, Month.JANUARY, 25)
            );

            Borrowed uwe = new Borrowed(
                    "112",
                    "12325",
                    LocalDate.of(2000, Month.JANUARY, 25)
            );

            Borrowed uwe1 = new Borrowed(
                    "113",
                    "12326",
                    LocalDate.of(2000, Month.JANUARY, 25)
            );

            repository.saveAll(
                    List.of(brittMari, uwe, uwe1)
            );
        };
        }
}
