package com.niteesh.budget_screen_backend.repository;

import com.niteesh.budget_screen_backend.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}

