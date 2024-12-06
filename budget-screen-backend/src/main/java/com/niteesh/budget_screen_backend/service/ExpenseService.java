package com.niteesh.budget_screen_backend.service;

import com.niteesh.budget_screen_backend.model.Budget;
import com.niteesh.budget_screen_backend.model.Expense;
import com.niteesh.budget_screen_backend.repository.BudgetRepository;
import com.niteesh.budget_screen_backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;

    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
    }

    public Expense addExpense(Long budgetId, Expense expense) {
        Budget budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new RuntimeException("Budget not found"));

        // Save the expense
        expense.setBudget(budget);
        Expense savedExpense = expenseRepository.save(expense);

        // Update the budget's current expenses
        budget.setCurrentExpenses(budget.getCurrentExpenses() + expense.getAmount());
        budgetRepository.save(budget);

        return savedExpense;
    }

    public Expense updateExpense(Long expenseId, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expense not found"));

        Budget budget = existingExpense.getBudget();

        // Adjust the budget's current expenses
        double difference = updatedExpense.getAmount() - existingExpense.getAmount();
        budget.setCurrentExpenses(budget.getCurrentExpenses() + difference);

        // Update and save the expense
        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setAmount(updatedExpense.getAmount());
        expenseRepository.save(existingExpense);

        // Save the updated budget
        budgetRepository.save(budget);

        return existingExpense;
    }

    public List<Expense> getAllExpensesForBudget(Long budgetId) {
        return expenseRepository.findByBudgetId(budgetId);
    }
}



