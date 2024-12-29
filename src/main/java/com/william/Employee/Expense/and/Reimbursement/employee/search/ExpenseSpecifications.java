package com.william.Employee.Expense.and.Reimbursement.employee.search;

import com.william.Employee.Expense.and.Reimbursement.employee.entities.Expense;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseSpecifications {

    public static Specification<Expense> filterByMonthAndYear(Integer month, Integer year) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (month != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.function("MONTH", Integer.class, root.get("currentDate")), month
                        )
                );
            }
            if (year != null) {
                predicates.add(
                        criteriaBuilder.equal(
                                criteriaBuilder.function("YEAR", Integer.class, root.get("currentDate")), year
                        )
                );
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
