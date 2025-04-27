package com.example.bookapi;

import com.example.bookapi.model.Book;
import com.example.bookapi.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (bookRepository.count() == 0) {
            Book b1 = new Book("How dumb Spiderwoman is!", "Robert C. Martin", "9780132350884",1999.0);
            Book b2 = new Book("Delice Boycott", "Hamdi el meddib", "9780134685991",899.0);
            Book b3 = new Book("Spiderwoman", "Andrew Garfield", "9780201616224",125.0);

            bookRepository.save(b1);
            bookRepository.save(b2);
            bookRepository.save(b3);

            System.out.println("📚 Sample books inserted!");
        } else {
            System.out.println("📚 Books already exist, skipping insert.");
        }
    }
}
