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

    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
    }

    public Budget updateBudget(Long id, Budget updatedBudget) {
        Budget existingBudget = budgetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Budget not found"));
        existingBudget.setName(updatedBudget.getName());
        existingBudget.setTotalAmount(updatedBudget.getTotalAmount());
        return budgetRepository.save(existingBudget);
    }
}



