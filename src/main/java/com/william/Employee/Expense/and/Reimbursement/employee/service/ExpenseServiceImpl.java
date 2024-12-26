package com.william.Employee.Expense.and.Reimbursement.employee.service;

import com.william.Employee.Expense.and.Reimbursement.employee.controller.NotificationController;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpenseDto;
import com.william.Employee.Expense.and.Reimbursement.employee.dto.ExpensePaginationResponse;
import com.william.Employee.Expense.and.Reimbursement.employee.entities.Expense;
import com.william.Employee.Expense.and.Reimbursement.employee.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


@Service
public class ExpenseServiceImpl implements ExpenseService {


        private final ExpenseRepository expenseRepository;
        private final FileService fileService;
        private final NotificationController notificationController;


        public ExpenseServiceImpl(ExpenseRepository expenseRepository, FileService fileService, NotificationController notificationController) {
            this.expenseRepository = expenseRepository;
            this.fileService = fileService;
            this.notificationController = notificationController;
        }

        @Value("${base.url}")
        private String baseUrl;

        @Value("${project.image}")
        private String path;

        @Override
        public ExpenseDto logExpense(ExpenseDto expenseDto, MultipartFile file) throws IOException {
            // check if file is selected
            if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
                throw new RuntimeException("file already exists, please seect another file");
            }
            // get the file name
            String uploadedFileName = fileService.uploadFile(path, file);

            // set the field image as file name
            expenseDto.setImage(uploadedFileName);

            // map the movie object with the dto object
            Expense movie = new Expense(
                    null,
                    expenseDto.getNameOfEmployee(),
                    expenseDto.getExpenseType(),
                    expenseDto.getCurrentDate(),
                    expenseDto.getExpenseDescription(),
                    expenseDto.getEmployeeEmploymentId(),
                    expenseDto.getPhoneNumber(),
                    expenseDto.getImage()
            );

            Expense savedMovie = expenseRepository.save(movie);

            String imageUrl = baseUrl + "/file/" + uploadedFileName;

            // map dto object to movie object
            ExpenseDto response = new ExpenseDto(
                    savedMovie.getEmployeeId(),
                    savedMovie.getNameOfEmployee(),
                    savedMovie.getExpenseType(),
                    savedMovie.getCurrentDate(),
                    savedMovie.getExpenseDescription(),
                    savedMovie.getEmployeeEmploymentId(),
                    savedMovie.getPhoneNumber(),
                    savedMovie.getImage(),
                    imageUrl
            );

            notificationController.sendNewExpenseNotification(response);
            return response;
        }

        @Cacheable(value ="movie", key="#movieId")
        @Override
        public ExpenseDto getExpense(Integer movieId) {

            Expense expenses = expenseRepository.findById(movieId).
                    orElseThrow(() -> new RuntimeException("expense not found"));
            String imageUrl = baseUrl + File.separator + expenses.getImage();

            ExpenseDto response = new ExpenseDto(
                    expenses.getEmployeeId(),
                    expenses.getNameOfEmployee(),
                    expenses.getExpenseType(),
                    expenses.getCurrentDate(),
                    expenses.getExpenseDescription(),
                    expenses.getEmployeeEmploymentId(),
                    expenses.getPhoneNumber(),
                    expenses.getImage(),
                    imageUrl
            );
            return response;
        }

        @Cacheable(value = "movie", key = "'allMovies'")
        @Override
        public List<ExpenseDto> getAllExpenses() {

            List<Expense> movies = expenseRepository.findAll();

            List<ExpenseDto> allExpenses = new ArrayList<>();

            for(Expense expense: movies ){
                String imageUrl = baseUrl + File.separator + expense.getImage();
                ExpenseDto expenseDto = new ExpenseDto(
                        expense.getEmployeeId(),
                        expense.getNameOfEmployee(),
                        expense.getExpenseType(),
                        expense.getCurrentDate(),
                        expense.getExpenseDescription(),
                        expense.getEmployeeEmploymentId(),
                        expense.getPhoneNumber(),
                        expense.getImage(),
                        imageUrl
                );

                allExpenses.add(expenseDto);
            }


            return allExpenses;
        }


        @Override
        public ExpenseDto updateExpense(Integer movieId, ExpenseDto expenseDto, MultipartFile file) throws IOException {
            Expense mv = expenseRepository.findById(movieId).
                    orElseThrow(() -> new RuntimeException("movie not found"));

            // if file is nulldo nothing
            // if file is not null
            String fileName = mv.getImage();
            if(file != null){
                Files.deleteIfExists(Paths.get(path + File.separator + fileName));
                fileName = fileService.uploadFile(path, file);
            }

            expenseDto.setImage(fileName);

            Expense expense = new Expense(
                    mv.getEmployeeId(),
                    expenseDto.getNameOfEmployee(),
                    expenseDto.getExpenseType(),
                    expenseDto.getCurrentDate(),
                    expenseDto.getExpenseDescription(),
                    expenseDto.getEmployeeEmploymentId(),
                    expenseDto.getPhoneNumber(),
                    expenseDto.getImage()
            );

            Expense updatedMovie = expenseRepository.save(expense);

            String imageUrl = baseUrl + File.separator + fileName;

            ExpenseDto response = new ExpenseDto(
                    expense.getEmployeeId(),
                    expense.getNameOfEmployee(),
                    expense.getExpenseType(),
                    expense.getCurrentDate(),
                    expense.getExpenseDescription(),
                    expense.getEmployeeEmploymentId(),
                    expense.getPhoneNumber(),
                    expense.getImage(),
                    imageUrl
            );

            return response;
        }

        @CacheEvict(value ="movie", key="#movieId")
        @Override
        public String deleteExpense(Integer movieId) throws IOException {

            Expense mv = expenseRepository.findById(movieId).
                    orElseThrow(() -> new RuntimeException("movie not found"));
            Integer id = mv.getEmployeeId();

            Files.deleteIfExists(Paths.get(path + File.separator + mv.getImage()));
            expenseRepository.delete(mv);

            return "Movie deleted with Id" + id;

        }

        @Override
        public ExpensePaginationResponse  getAllExpensePagination(Integer pageNum, Integer pageSize) {
            Pageable page = PageRequest.of(pageNum, pageSize);
            Page<Expense> expensePages = expenseRepository.findAll(page);

            List<Expense> allExpenses = expensePages.getContent();

            List<ExpenseDto> newAllExpenses = new ArrayList<>();

            for(Expense expense: allExpenses){
                String imageUrl = baseUrl + File.separator + expense.getImage();
                ExpenseDto expenseDto = new ExpenseDto(
                        expense.getEmployeeId(),
                        expense.getNameOfEmployee(),
                        expense.getExpenseType(),
                        expense.getCurrentDate(),
                        expense.getExpenseDescription(),
                        expense.getEmployeeEmploymentId(),
                        expense.getPhoneNumber(),
                        expense.getImage(),
                        imageUrl
                );
                newAllExpenses.add(expenseDto);
            }

            return new ExpensePaginationResponse (newAllExpenses, pageNum, pageSize,
                    expensePages.getTotalElements(),
                    expensePages.getTotalPages(),
                    expensePages.isLast());
        }

        @Override
        public ExpensePaginationResponse getAllExpensePaginationSorting(Integer pageNum, Integer pageSize,
                                                                     String sortBy, String dir) {

            Sort sort = dir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending()
                    : Sort.by(sortBy).descending();

            Pageable page = PageRequest.of(pageNum, pageSize, sort);
            Page<Expense> expensePages = expenseRepository.findAll(page);

            List<Expense> allExpenses = expensePages.getContent();

            List<ExpenseDto> newAllExpenses = new ArrayList<>();

            for(Expense expense: allExpenses){
                String imageUrl = baseUrl + File.separator + expense.getImage();
                ExpenseDto movieDto = new ExpenseDto(
                        expense.getEmployeeId(),
                        expense.getNameOfEmployee(),
                        expense.getExpenseType(),
                        expense.getCurrentDate(),
                        expense.getExpenseDescription(),
                        expense.getEmployeeEmploymentId(),
                        expense.getPhoneNumber(),
                        expense.getImage(),
                        imageUrl
                );
                newAllExpenses.add(movieDto);
            }

            return new ExpensePaginationResponse(newAllExpenses, pageNum, pageSize,
                    expensePages.getTotalElements(),
                    expensePages.getTotalPages(),
                    expensePages.isLast());
        }


}
