package com.william.Employee.Expense.and.Reimbursement.employee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpenseDto;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpensePaginationResponse;
import com.william.Employee.Expense.and.Reimbursement.employee.service.ExpenseService;
import com.william.Employee.Expense.and.Reimbursement.employee.utils.AppConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseController {


        private final ExpenseService expenseService;

        public ExpenseController(ExpenseService expenseService) {
            this.expenseService = expenseService;
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @PostMapping("/log-expense")
        public ResponseEntity<ExpenseDto> addExpenseHandler(@RequestPart String expenseDto,
                                                          @RequestPart MultipartFile file) throws IOException {
            if(file.isEmpty()){
                throw new RuntimeException("please select file");
            }
            ExpenseDto dto = convertDtoObjectToJson(expenseDto);
            return new ResponseEntity<>(expenseService.logExpense(dto, file), HttpStatus.CREATED);
        }

        private ExpenseDto convertDtoObjectToJson(String expenseDtoObj) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(expenseDtoObj, ExpenseDto.class);
        }

        @GetMapping("/get-expense/{employeeId}")
        public ResponseEntity<ExpenseDto> getExpenseHandler(@PathVariable Integer employeeId){
            return ResponseEntity.ok(expenseService.getExpense(employeeId));
        }

        @GetMapping("/get-all-expense")
        public ResponseEntity<List<ExpenseDto>> getAllExpenseHandler(){
            return ResponseEntity.ok(expenseService.getAllExpenses());
        }

        @PreAuthorize("hasAuthority('ADMIN')")
        @PutMapping("/update-expense/{employeeId}")
        public ResponseEntity<ExpenseDto> updateExpenseHandler(@PathVariable Integer employeeId,
                                                           @RequestPart String expenseDto,
                                                           @RequestPart MultipartFile file) throws IOException {

            if(file.isEmpty()){
                throw new RuntimeException("file not selected");
            }
            ExpenseDto dto = convertDtoObjectToJson(expenseDto);
            return ResponseEntity.ok(expenseService.updateExpense(employeeId, dto, file));
        }

        @DeleteMapping("/delete-expense/{employeeId}")
        public ResponseEntity<String> deleteExpenseHandler(@PathVariable Integer employeeId) throws IOException {
            return ResponseEntity.ok(expenseService.deleteExpense(employeeId));
        }

        @GetMapping("/getAllExpenseWithPagination")
        public ResponseEntity<ExpensePaginationResponse> getAllExpenseWithPaginationHandler(
                @RequestParam(defaultValue = AppConstant.PAGE_NUM, required = false) Integer pageNum,
                @RequestParam(defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize){

            return ResponseEntity.ok(expenseService.getAllExpensePagination(pageNum,pageSize));
        }


        @GetMapping("/getAllExpenseWithPaginationSort")
        public ResponseEntity<ExpensePaginationResponse> getAllExpenseWithPaginationSortingHandler(
                @RequestParam(defaultValue = AppConstant.PAGE_NUM, required = false) Integer pageNum,
                @RequestParam(defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
                @RequestParam(defaultValue = AppConstant.SORT_BY, required = false) String sortBy,
                @RequestParam(defaultValue = AppConstant.DIR, required = false) String dir){

            return ResponseEntity.ok(expenseService.getAllExpensePaginationSorting(pageNum,pageSize,
                    sortBy, dir));
        }



}
