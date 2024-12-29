package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

public class FileIsEmptyException extends RuntimeException{
    public FileIsEmptyException(String message){
        super(message);
    }
}
