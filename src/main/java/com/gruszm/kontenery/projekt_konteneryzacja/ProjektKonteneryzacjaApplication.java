package com.gruszm.kontenery.projekt_konteneryzacja;

import com.gruszm.kontenery.projekt_konteneryzacja.entities.Book;
import com.gruszm.kontenery.projekt_konteneryzacja.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjektKonteneryzacjaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjektKonteneryzacjaApplication.class, args);
	}

	@Bean
	public CommandLineRunner cmd(BookRepository bookRepository)
	{
		return runner ->
		{
			List<Book> books = bookRepository.findAll();

			System.out.println("------------Books in the database------------");
			books.forEach(book -> System.out.println(book));
		};
	}
}
