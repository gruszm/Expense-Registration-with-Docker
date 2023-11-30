package com.gruszm.kontenery.controllers;

import com.gruszm.kontenery.entities.Category;
import com.gruszm.kontenery.entities.Expense;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/expenses")
public class ExpenseController
{
    @Value("${my.app.rest.api.host}")
    private String host;

    @Value("${my.app.rest.api.port}")
    private String port;

    @GetMapping
    public String getAllExpenses(Model model)
    {
        String url = "http://" + host + ":" + port + "/api/expenses";

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Expense[]> expensesResponse = restTemplate.getForEntity(url, Expense[].class);
        List<Expense> expenses = Arrays.asList(expensesResponse.getBody());

        model.addAttribute("expenses", expenses);

        return "expenses/show-expenses";
    }

    @GetMapping("/expenseForm")
    public String getExpenseForm(Model model)
    {
        String url = "http://" + host + ":" + port + "/api/categories";

        Expense expense = new Expense();
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Category[]> categoriesResponse = restTemplate.getForEntity(url, Category[].class);
        List<Category> categories = Arrays.asList(categoriesResponse.getBody());

        model.addAttribute("expense", expense);
        model.addAttribute("categories", categories);

        return "expenses/expense-form";
    }

    @PostMapping("/processExpenseForm")
    public String processExpenseForm(@ModelAttribute(name = "expense") Expense expense, Model model, RedirectAttributes redirectAttributes)
    {
        // trim the id to null, if it's an empty string
        expense.setId(expense.getId().equals("") ? null : expense.getId());

        String url = "http://" + host + ":" + port + "/api/expenses";

        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Expense> requestEntity = new HttpEntity<>(expense, headers);
        ResponseEntity<Expense> expenseResponse = restTemplate.postForEntity(url, requestEntity, Expense.class);

        redirectAttributes.addAttribute("expenseAdded", "");

        return "redirect:/expenses";
    }

    @PostMapping("/deleteExpense")
    public String deleteExpense(@RequestParam(name = "expenseIdToDelete") String id, RedirectAttributes redirectAttributes)
    {
        String url = "http://" + host + ":" + port + "/api/expenses/id/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Expense> expenseResponse = restTemplate.exchange(url, HttpMethod.DELETE, HttpEntity.EMPTY, Expense.class);

        redirectAttributes.addAttribute("expenseDeleted", "");

        return "redirect:/expenses";
    }

    @PostMapping("/modifyExpense")
    public String modifyExpense(@RequestParam(name = "expenseIdToModify") String id, Model model)
    {
        String expenseUrl = "http://" + host + ":" + port + "/api/expenses/id/" + id;
        String categoriesUrl = "http://" + host + ":" + port + "/api/categories";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Expense> expenseResponse = restTemplate.getForEntity(expenseUrl, Expense.class);
        ResponseEntity<Category[]> categoriesResponse = restTemplate.getForEntity(categoriesUrl, Category[].class);

        model.addAttribute("expense", expenseResponse.getBody());
        model.addAttribute("categories", Arrays.asList(categoriesResponse.getBody()));

        return "expenses/expense-form";
    }
}
