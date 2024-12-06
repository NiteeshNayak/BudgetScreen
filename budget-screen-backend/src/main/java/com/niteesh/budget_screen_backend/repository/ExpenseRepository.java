package com.niteesh.budget_screen_backend.repository;

import com.niteesh.budget_screen_backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("SELECT e FROM Expense e WHERE e.budget.id = :budgetId")
    List<Expense> findByBudgetId(@Param("budgetId") Long budgetId);
}

