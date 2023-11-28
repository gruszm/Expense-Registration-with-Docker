package com.gruszm.kontenery.projekt_konteneryzacja;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import com.gruszm.kontenery.projekt_konteneryzacja.services.CategoryService;
import com.gruszm.kontenery.projekt_konteneryzacja.services.ExpenseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MyAppRestApiApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(MyAppRestApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CategoryService categoryService, ExpenseService expenseService)
    {
        return runner ->
        {
            Category c1 = new Category("category");
            Category c2 = new Category("category2");
            Category c3 = new Category("category3");

            Expense e1 = new Expense("ziemniaki", "ziemniaki na obiad", 8.00, c2);
            Expense e2 = new Expense("jaja", "jaja na sniadanie", 3.5, c1);
            Expense e3 = new Expense("marchew", "marchew do salatki", 1.8, c3);
            Expense e4 = new Expense("czapka", "czapka na zime", 19.99, c3);

            try
            {
                categoryService.saveAll(c1, c2, c3);
                expenseService.saveAll(e1, e2, e3, e4);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        };
    }
}
