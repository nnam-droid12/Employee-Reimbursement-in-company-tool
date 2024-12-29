package com.william.Employee.Expense.and.Reimbursement.employee.Exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(FileNameExistException.class)
    public ProblemDetail FileNameExistException(FileNameExistException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }


    @ExceptionHandler(FileIsEmptyException.class)
    public ProblemDetail FileIsEmptyException(FileIsEmptyException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ProblemDetail ExpenseNotFoundException(ExpenseNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ProblemDetail RefreshTokenExpiredException(RefreshTokenExpiredException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.EXPECTATION_FAILED, ex.getMessage());

    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ProblemDetail RefreshTokenNotFoundException(RefreshTokenNotFoundException ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(ExpenseNotFoundByMonthAndYear.class)
    public ProblemDetail ExpenseNotFoundByMonthAndYear(ExpenseNotFoundByMonthAndYear  ex){
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
}
