package com.niteesh.budget_screen_backend.repository;


import com.niteesh.budget_screen_backend.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
