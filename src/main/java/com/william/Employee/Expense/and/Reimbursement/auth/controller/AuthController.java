package com.william.Employee.Expense.and.Reimbursement.auth.controller;




import com.william.Employee.Expense.and.Reimbursement.auth.entities.RefreshToken;
import com.william.Employee.Expense.and.Reimbursement.auth.entities.User;
import com.william.Employee.Expense.and.Reimbursement.auth.repository.UserRepository;
import com.william.Employee.Expense.and.Reimbursement.auth.service.AuthService;
import com.william.Employee.Expense.and.Reimbursement.auth.service.JwtService;
import com.william.Employee.Expense.and.Reimbursement.auth.service.RefreshTokenService;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.AuthResponse;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.LoginRequest;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.RefreshTokenRequest;
import com.william.Employee.Expense.and.Reimbursement.auth.utils.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, UserRepository userRepository, RefreshTokenService refreshTokenService, JwtService jwtService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerHandler(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(authService.login(loginRequest));
    }


    @PostMapping("/admin-login")
    public ResponseEntity<AuthResponse> adminLogin(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.adminLogin(loginRequest));

    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshTokenHandler(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();
        String accessToken = jwtService.generateToken(user, "USER");

        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getRefreshToken())
                .build());

    }
}

