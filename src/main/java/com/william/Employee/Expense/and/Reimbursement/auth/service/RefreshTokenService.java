package com.william.Employee.Expense.and.Reimbursement.auth.service;



import com.william.Employee.Expense.and.Reimbursement.auth.entities.RefreshToken;
import com.william.Employee.Expense.and.Reimbursement.auth.entities.User;
import com.william.Employee.Expense.and.Reimbursement.auth.repository.RefreshTokenRepository;
import com.william.Employee.Expense.and.Reimbursement.auth.repository.UserRepository;
import com.william.Employee.Expense.and.Reimbursement.employee.Exception.RefreshTokenExpiredException;
import com.william.Employee.Expense.and.Reimbursement.employee.Exception.RefreshTokenNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found"));

        RefreshToken refreshToken = user.getRefreshToken();

        if(refreshToken == null){
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(5*60*60*1000))
                    .user(user)
                    .build();
            refreshTokenRepository.save(refreshToken);
        }
        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken){
        RefreshToken refToken = refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new RefreshTokenNotFoundException("refreshToken not found"));

        if(refToken.getExpirationTime().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(refToken);
            throw new RefreshTokenExpiredException("refresh token expired");
        }
        return refToken;
    }

    public void deleteByRefreshToken(String email) {
        // Retrieve the user by email
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email " + email));

        // Retrieve the RefreshToken object from the user
        var refreshToken = user.getRefreshToken(); // Assuming user.getRefreshToken() returns a RefreshToken object
        if (refreshToken == null) {
            throw new RuntimeException("No refresh token found for user with email " + email);
        }

        // Retrieve the actual token string from the RefreshToken object
        var refreshTokenValue = refreshToken.getRefreshToken();
        if (refreshTokenValue == null || refreshTokenValue.isBlank()) {
            throw new RuntimeException("Refresh token value is invalid for user with email " + email);
        }

        // Retrieve the refresh token entity by its value
        var refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshTokenValue)
                .orElseThrow(() -> new RuntimeException("Refresh token not found for value " + refreshTokenValue));

        // Delete the refresh token entity
        refreshTokenRepository.delete(refreshTokenEntity);
    }



}


