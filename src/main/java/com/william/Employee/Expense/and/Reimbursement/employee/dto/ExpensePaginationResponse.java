package com.william.Employee.Expense.and.Reimbursement.employee.dto;

import java.util.List;



public record ExpensePaginationResponse(List<ExpenseDto> moviedto,
                                          Integer PageNum,
                                          Integer PageSize,
                                          long totalElements,
                                          int totalPages,
                                          boolean isLast) {


}
