package com.niteesh.budget_screen_backend.service;

import com.niteesh.budget_screen_backend.model.Budget;
import com.niteesh.budget_screen_backend.model.Expense;
import com.niteesh.budget_screen_backend.model.ExpenseHistory;
import com.niteesh.budget_screen_backend.repository.BudgetRepository;
import com.niteesh.budget_screen_backend.repository.ExpenseHistoryRepository;
import com.niteesh.budget_screen_backend.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final BudgetRepository budgetRepository;
    private final ExpenseHistoryRepository expenseHistoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, BudgetRepository budgetRepository,
                          ExpenseHistoryRepository expenseHistoryRepository) {
        this.expenseRepository = expenseRepository;
        this.budgetRepository = budgetRepository;
        this.expenseHistoryRepository = expenseHistoryRepository;
    }

    public Expense addExpense(Long budgetId, Expense expense) {
        Budget budget = budgetRepository.findById(budgetId).orElseThrow(() -> new RuntimeException("Budget not found"));
        expense.setBudget(budget);

        Expense savedExpense = expenseRepository.save(expense);
        logExpenseHistory(savedExpense, "CREATED");
        return savedExpense;
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));

        existingExpense.setDescription(updatedExpense.getDescription());
        existingExpense.setAmount(updatedExpense.getAmount());

        Expense savedExpense = expenseRepository.save(existingExpense);
        logExpenseHistory(savedExpense, "UPDATED");
        return savedExpense;
    }

    public List<ExpenseHistory> getExpenseHistoryForBudget(Long budgetId) {
        List<Expense> expenses = expenseRepository.findByBudgetId(budgetId);
        return expenses.stream()
                .flatMap(expense -> expenseHistoryRepository.findByExpenseId(expense.getId()).stream())
                .collect(Collectors.toList());
    }

    private void logExpenseHistory(Expense expense, String action) {
        ExpenseHistory history = new ExpenseHistory();
        history.setExpense(expense);
        history.setDescription(expense.getDescription());
        history.setAmount(expense.getAmount());
        history.setAction(action);
        history.setTimestamp(LocalDateTime.now());

        expenseHistoryRepository.save(history);
    }
}


