package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

public class ExpenseNotFoundByMonthAndYear extends RuntimeException{
    public ExpenseNotFoundByMonthAndYear(String message){
        super(message);
    }
}
