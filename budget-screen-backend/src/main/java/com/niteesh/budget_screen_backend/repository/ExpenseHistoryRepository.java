package com.niteesh.budget_screen_backend.repository;

import com.niteesh.budget_screen_backend.model.ExpenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseHistoryRepository extends JpaRepository<ExpenseHistory, Long> {
    List<ExpenseHistory> findByExpenseId(Long expenseId);
}

