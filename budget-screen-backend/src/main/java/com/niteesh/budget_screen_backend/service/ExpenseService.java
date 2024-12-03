package com.niteesh.budget_screen_backend.service;


import com.niteesh.budget_screen_backend.model.Budget;
import com.niteesh.budget_screen_backend.model.Expense;
import com.niteesh.budget_screen_backend.repository.BudgetRepository;
import com.niteesh.budget_screen_backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public Expense addExpense(Long budgetId, Expense expense) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget not found"));
        expense.setBudget(budget);
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        expense.setDescription(updatedExpense.getDescription());
        expense.setAmount(updatedExpense.getAmount());
        return expenseRepository.save(expense);
    }
}
