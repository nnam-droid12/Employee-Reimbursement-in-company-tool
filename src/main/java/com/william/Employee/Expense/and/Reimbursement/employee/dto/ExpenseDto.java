package com.william.Employee.Expense.and.Reimbursement.employee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


public class ExpenseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer employeeId;


    @Column(nullable = false)
    @NotBlank(message = "nameOfEmployee cannot be blank")
    private String nameOfEmployee;


    @Column(nullable = false)
    @NotBlank(message = "ExpenseType cannot be blank")
    private String expenseType;

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


    @JsonProperty("imageUrl")
        @NotNull(message = "image url cannot be empty")
        private String imageurl;


        public ExpenseDto(Integer employeeId,
                          @NotBlank(message = "nameOfEmployee cannot be blank") String nameOfEmployee,
                          @NotBlank(message = "expenseType cannot be blank") String expenseType,
                          Date currentDate,
                          @NotBlank(message = "expenseDescription cannot be blank") String expenseDescription,
                          @NotBlank(message = "employeeEmploymentId cannot be blank") String employeeEmploymentId,
                          @NotNull(message = "phoneNumber year cannot be blank") Integer phoneNumber,
                          @NotBlank(message = "image cannot be blank") String image,
                          @NotNull(message = "image url cannot be empty") String imageurl) {
            this.employeeId = employeeId;
            this.nameOfEmployee = nameOfEmployee;
            this.expenseType = expenseType;
            this.currentDate = currentDate;
            this.expenseDescription = expenseDescription;
            this.employeeEmploymentId = employeeEmploymentId;
            this.phoneNumber = phoneNumber;
            this.image = image;
            this.imageurl = imageurl;
        }


        public void setImage(String image) {
            this.image = image;
        }

        public Integer getEmployeeId() {
            return this.employeeId;
        }

        public @NotBlank(message = "nameOfEmployee cannot be blank") String getNameOfEmployee() {
            return this.nameOfEmployee;
        }

        public @NotBlank(message = "expenseType cannot be blank") String getExpenseType() {
            return this.expenseType;
        }

    public Date getCurrentDate() {
        return this.currentDate;
    }

        public @NotBlank(message = "expenseDescription cannot be blank") String getExpenseDescription() {
            return this.expenseDescription;
        }

    public @NotBlank(message = "employeeEmploymentId cannot be blank") String getEmployeeEmploymentId() {
        return this.employeeEmploymentId;
    }



        public @NotNull(message = "phoneNumber year cannot be blank") Integer getPhoneNumber() {
            return this.phoneNumber;
        }


        public @NotBlank(message = "image cannot be blank") String getImage() {
            return this.image;
        }

        public @NotNull(message = "image url cannot be empty") String getImageurl() {
            return this.imageurl;
        }


}
