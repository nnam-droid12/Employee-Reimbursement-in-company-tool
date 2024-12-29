package com.william.Employee.Expense.and.Reimbursement.employee.Exception;


public class FileNameExistException extends RuntimeException{
    public FileNameExistException(String message){
        super(message);
    }
}


