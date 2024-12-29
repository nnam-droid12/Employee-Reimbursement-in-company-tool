package com.william.Employee.Expense.and.Reimbursement.auth.service;


import com.william.Employee.Expense.and.Reimbursement.auth.entities.User;
import com.william.Employee.Expense.and.Reimbursement.auth.entities.UserRole;
import com.william.Employee.Expense.and.Reimbursement.auth.repository.UserRepository;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.AuthResponse;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.LoginRequest;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.LogoutRequest;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.RegisterRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;


    public AuthService(PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       RefreshTokenService refreshTokenService,
                       UserRepository userRepository,
                       AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;



    }

    @Value("${admin.email}")
    private String defaultAdminEmail;

    @Value("${admin.password}")
    private String defaultAdminPassword;

    public AuthResponse register(RegisterRequest registerRequest){
        var user = User.builder()
                .name(registerRequest.getName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);

        var accessToken = jwtService.generateToken(savedUser, "USER");

        var refreshToken = refreshTokenService.createRefreshToken(savedUser.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );
        var user = userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        var accessToken = jwtService.generateToken(user, "USER");

        var refreshToken = refreshTokenService.createRefreshToken(loginRequest.getEmail());

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }

    public void logout(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank.");
        }
        refreshTokenService.deleteByRefreshToken(email);
    }


    public AuthResponse adminLogin(LoginRequest loginRequest) {
        if (!defaultAdminEmail.equals(loginRequest.getEmail())) {
            throw new RuntimeException("Invalid email");
        }

        User admin = userRepository.findByEmail(defaultAdminEmail)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        if (!passwordEncoder.matches(loginRequest.getPassword(), admin.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        var accessToken = jwtService.generateToken(admin, "ADMIN"); // Ensure 'admin' implements UserDetails
        var refreshToken = refreshTokenService.createRefreshToken(defaultAdminEmail);

        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build();
    }


}


