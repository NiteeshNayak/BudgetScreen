package com.niteesh.budget_screen_backend.controller;

import com.niteesh.budget_screen_backend.model.Expense;
import com.niteesh.budget_screen_backend.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/{budgetId}")
    public Expense addExpense(@PathVariable Long budgetId, @RequestBody Expense expense) {
        return expenseService.addExpense(budgetId, expense);
    }

    @PutMapping("/{expenseId}")
    public Expense updateExpense(@PathVariable Long expenseId, @RequestBody Expense expense) {
        return expenseService.updateExpense(expenseId, expense);
    }

    @GetMapping("/budget/{budgetId}")
    public List<Expense> getExpensesForBudget(@PathVariable Long budgetId) {
        return expenseService.getAllExpensesForBudget(budgetId);
    }
}


