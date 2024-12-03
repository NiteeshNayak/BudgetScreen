package com.niteesh.budget_screen_backend.service;


import com.niteesh.budget_screen_backend.model.Budget;
import com.niteesh.budget_screen_backend.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public Budget addBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget updateBudget(Long id, Budget updatedBudget) {
        Budget budget = budgetRepository.findById(id).orElseThrow(() -> new RuntimeException("Budget not found"));
        budget.setName(updatedBudget.getName());
        budget.setTotalAmount(updatedBudget.getTotalAmount());
        return budgetRepository.save(budget);
    }

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }
}

