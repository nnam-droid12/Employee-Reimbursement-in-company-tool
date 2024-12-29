package com.william.Employee.Expense.and.Reimbursement.employee.repository;

import com.william.Employee.Expense.and.Reimbursement.employee.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExpenseRepository extends JpaRepository<Expense, Integer>, JpaSpecificationExecutor<Expense> {


}
