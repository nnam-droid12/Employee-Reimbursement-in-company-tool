package com.william.Employee.Expense.and.Reimbursement.employee.service;

import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpenseDto;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpensePaginationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ExpenseService {


    ExpenseDto logExpense(ExpenseDto expenseDto, MultipartFile file) throws IOException;

    ExpenseDto getExpense(Integer employeeId);

    List<ExpenseDto> getAllExpenses();

    ExpenseDto updateExpense(Integer employeeId, ExpenseDto expenseDto, MultipartFile file) throws IOException;

    String deleteExpense(Integer employeeId) throws IOException;

    ExpensePaginationResponse getAllExpensePagination(Integer pageNum, Integer pageSize);

    ExpensePaginationResponse getAllExpensePaginationSorting(Integer pageNum, Integer pageSize,
                                                          String sortBy, String dir);

    List<ExpenseDto> searchExpensesByMonthAndYear(Integer month, Integer year);
}


