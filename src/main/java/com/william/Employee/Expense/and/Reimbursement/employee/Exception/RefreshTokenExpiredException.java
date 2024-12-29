package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

public class RefreshTokenExpiredException extends RuntimeException{
    public RefreshTokenExpiredException(String message){
        super(message);
    }
}
