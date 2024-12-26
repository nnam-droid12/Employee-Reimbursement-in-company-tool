package com.william.Employee.Expense.and.Reimbursement.employee.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;


@Entity
public class Expense implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer employeeId;


        @Column(nullable = false)
        @NotBlank(message = "nameOfEmployee cannot be blank")
        private String nameOfEmployee;


        @Column(nullable = false)
        @NotBlank(message = "ExpenseType cannot be blank")
        private String expenseType;

        @Column(nullable = false)
        private Date currentDate;


        @Column(nullable = false)
        @NotBlank(message = "ExpenseDescription cannot be blank")
        private String expenseDescription;

        @Column(nullable = false)
        @NotBlank(message = "EmployeeEmploymentId cannot be blank")
        private String employeeEmploymentId;


        @Column(nullable = false)
        @NotNull(message = "phoneNumber year cannot be blank")
        private Integer phoneNumber;


        @Column(nullable = false)
        @NotBlank(message = "image cannot be blank")
        private String image;

        public Expense(Integer employeeId, @NotBlank(message = "nameOfEmployee cannot be blank") String nameOfEmployee,
                       @NotBlank(message = "expenseType cannot be blank") String expenseType,
                       Date currentDate,
                       @NotBlank(message = "expenseDescription cannot be blank") String expenseDescription,
                       @NotBlank(message = "employeeEmploymentId not blank") String employeeEmploymentId,
                       @NotNull(message = "phoneNumber year cannot be blank") Integer phoneNumber,
                       @NotBlank(message = "image cannot be blank") String image) {
            this.employeeId = employeeId;
            this.nameOfEmployee = nameOfEmployee;
            this.expenseType = expenseType;
            this.currentDate = currentDate;
            this.expenseDescription = expenseDescription;
            this.employeeEmploymentId = employeeEmploymentId;
            this.phoneNumber = phoneNumber;
            this.image = image;
        }

        public Expense() {
        }

        public Integer getEmployeeId() {
            return this.employeeId;
        }

        public @NotBlank(message = "nameOfEmployee cannot be blank") String getNameOfEmployee() {
            return this.nameOfEmployee;
        }

        public @NotBlank(message = "ExpenseType cannot be blank") String getExpenseType() {
            return this.expenseType;
        }

    public Date getCurrentDate() {
        return this.currentDate;
    }

        public @NotBlank(message = "ExpenseDescription cannot be blank") String getExpenseDescription() {
            return this.expenseDescription;
        }



        public
        @NotBlank(message = "EmployeeEmploymentId cannot be blank") String getEmployeeEmploymentId(){
            return this.employeeEmploymentId;
        }

        public @NotNull(message = "release year cannot be blank") Integer getPhoneNumber() {
            return this.phoneNumber;
        }

        public @NotBlank(message = "image cannot be blank") String getImage() {
            return this.image;
        }



}
