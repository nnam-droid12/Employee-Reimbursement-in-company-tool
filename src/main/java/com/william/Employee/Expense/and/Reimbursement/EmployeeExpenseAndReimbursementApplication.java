package com.william.Employee.Expense.and.Reimbursement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.naming.Context;
import java.util.logging.Logger;

@SpringBootApplication
public class EmployeeExpenseAndReimbursementApplication {


	private static final Logger log = Logger.getLogger(EmployeeExpenseAndReimbursementApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(EmployeeExpenseAndReimbursementApplication.class, args);

		log.info("Application started successfully");

	}

}
