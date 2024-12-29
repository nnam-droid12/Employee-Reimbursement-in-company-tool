package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException(String message){
        super(message);
    }
}
