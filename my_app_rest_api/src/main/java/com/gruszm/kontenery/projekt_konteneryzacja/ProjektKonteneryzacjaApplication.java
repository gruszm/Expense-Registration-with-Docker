package com.gruszm.kontenery.projekt_konteneryzacja;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.entities.User;
import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import com.gruszm.kontenery.projekt_konteneryzacja.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjektKonteneryzacjaApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ProjektKonteneryzacjaApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserService userService, CategoryService categoryService, ExpenseService expenseService)
    {
        return runner ->
        {
            User u1 = new User("email", "password", "firstname", "lastname");
            User u2 = new User("email2", "password2", "firstname2", "lastname2");
            User u3 = new User("email3", "password3", "firstname3", "lastname3");

            Category c1 = new Category("category");
            Category c2 = new Category("category2");
            Category c3 = new Category("category3");

            Expense e1 = new Expense("ziemniaki", "ziemniaki na obiad", u1, c2);
            Expense e2 = new Expense("jaja", "jaja na sniadanie", u2, c1);
            Expense e3 = new Expense("marchew", "marchew do salatki", u3, c3);
            Expense e4 = new Expense("czapka", "czapka na zime", u2, c3);

            userService.saveAll(u1, u2, u3);
            categoryService.saveAll(c1, c2, c3);
            expenseService.saveAll(e1, e2, e3, e4);
        };
    }
}
