package com.william.Employee.Expense.and.Reimbursement.auth.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "name of user cannot be blank")
    @Column(nullable = false)
    private String name;


    @NotBlank(message = "email of user cannot be blank")
    @Column(unique = true)
    @Email(message = "email must be in correct format")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Column(nullable = false)
    @Size(min = 5, message = "password must be 5 characters and above")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private ForgotPassword forgotPassword;

    @OneToOne(mappedBy = "user")
    private RefreshToken refreshToken;

    public User(Integer userId, @NotBlank(message = "name of user cannot be blank") String name,  @NotBlank(message = "email of user cannot be blank") @Email(message = "email must be in correct format") String email, @NotBlank(message = "password cannot be empty") @Size(min = 5, message = "password must be 5 characters and above") String password, UserRole role, ForgotPassword forgotPassword, RefreshToken refreshToken) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.forgotPassword = forgotPassword;
        this.refreshToken = refreshToken;
    }

    public User() {
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setEmail(String adminEmail) {
    }

    public void setPassword(String admin_password) {
    }

    public void setRole(String admin) {
    }

    public void setName(String defaultAdmin) {
    }


    public Integer getUserId() {
        return this.userId;
    }

    public @NotBlank(message = "name of user cannot be blank") String getName() {
        return this.name;
    }

    public @NotBlank(message = "email of user cannot be blank") @Email(message = "email must be in correct format") String getEmail() {
        return this.email;
    }

    public UserRole getRole() {
        return this.role;
    }

    public ForgotPassword getForgotPassword() {
        return this.forgotPassword;
    }

    public RefreshToken getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(RefreshToken refreshToken) {
    }

    public static class UserBuilder {
        private Integer userId;
        private @NotBlank(message = "name of user cannot be blank") String name;
        private @NotBlank(message = "email of user cannot be blank")
        @Email(message = "email must be in correct format") String email;
        private @NotBlank(message = "password cannot be empty")
        @Size(min = 5, message = "password must be 5 characters and above") String password;
        private UserRole role;
        private ForgotPassword forgotPassword;
        private RefreshToken refreshToken;

        UserBuilder() {
        }

        public UserBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public UserBuilder name(@NotBlank(message = "name of user cannot be blank") String name) {
            this.name = name;
            return this;
        }


        public UserBuilder email(@NotBlank(message = "email of user cannot be blank") @Email(message = "email must be in correct format") String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(@NotBlank(message = "password cannot be empty") @Size(min = 5, message = "password must be 5 characters and above") String password) {
            this.password = password;
            return this;
        }

        public UserBuilder role(UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder forgotPassword(ForgotPassword forgotPassword) {
            this.forgotPassword = forgotPassword;
            return this;
        }

        public UserBuilder refreshToken(RefreshToken refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public User build() {
            return new User(this.userId, this.name, this.email, this.password, this.role, this.forgotPassword, this.refreshToken);
        }

        public String toString() {
            return "User.UserBuilder(userId=" + this.userId + ", name=" + this.name + ", email=" + this.email + ", password=" + this.password + ", role=" + this.role + ", forgotPassword=" + this.forgotPassword + ", refreshToken=" + this.refreshToken + ")";
        }
    }
}


