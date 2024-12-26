package com.william.Employee.Expense.and.Reimbursement.auth.repository;



import com.william.Employee.Expense.and.Reimbursement.auth.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}


