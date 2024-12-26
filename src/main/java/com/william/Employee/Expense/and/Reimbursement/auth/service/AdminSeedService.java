package com.william.Employee.Expense.and.Reimbursement.auth.service;


import com.william.Employee.Expense.and.Reimbursement.auth.entities.User;
import com.william.Employee.Expense.and.Reimbursement.auth.entities.UserRole;
import com.william.Employee.Expense.and.Reimbursement.auth.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminSeedService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.email}")
    private String defaultAdminEmail;

    @Value("${admin.password}")
    private String defaultAdminPassword;

    public AdminSeedService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void seedAdmin() {
        // Check if the admin already exists by email
        Optional<User> adminOptional = userRepository.findByEmail(defaultAdminEmail);

        if (adminOptional.isEmpty()) {
            String hashedPassword = passwordEncoder.encode(defaultAdminPassword); // Hash the password before saving

            var adminUser = User.builder()
                    .email(defaultAdminEmail)
                    .password(hashedPassword)  // Save the hashed password
                    .role(UserRole.ADMIN)
                    .username("defaultAdmin")  // Provide a default username
                    .name("Default Admin")     // Provide a default name
                    .build();

            userRepository.save(adminUser);
            System.out.println("Default admin created with email: " + defaultAdminEmail);
        } else {
            System.out.println("Default admin already exists with email: " + defaultAdminEmail);
        }
    }
}

