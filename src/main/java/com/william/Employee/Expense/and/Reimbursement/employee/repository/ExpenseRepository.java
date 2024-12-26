package com.william.Employee.Expense.and.Reimbursement.employee.repository;

import com.william.Employee.Expense.and.Reimbursement.employee.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {


}
