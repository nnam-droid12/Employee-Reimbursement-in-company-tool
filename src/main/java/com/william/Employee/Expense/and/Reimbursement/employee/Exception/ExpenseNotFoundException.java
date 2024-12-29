package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

public class ExpenseNotFoundException extends RuntimeException{
    public ExpenseNotFoundException(String message){
        super(message);
    }
}
