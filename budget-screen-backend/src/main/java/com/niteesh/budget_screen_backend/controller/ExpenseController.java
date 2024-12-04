package com.niteesh.budget_screen_backend.controller;

import com.niteesh.budget_screen_backend.model.Expense;
import com.niteesh.budget_screen_backend.model.ExpenseHistory;
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

    @PutMapping("/{id}")
    public Expense updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        return expenseService.updateExpense(id, expense);
    }

    @GetMapping("/history/budget/{budgetId}")
    public List<ExpenseHistory> getExpenseHistoryForBudget(@PathVariable Long budgetId) {
        return expenseService.getExpenseHistoryForBudget(budgetId);
    }
}


