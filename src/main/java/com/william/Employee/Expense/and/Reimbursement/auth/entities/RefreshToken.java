package com.william.Employee.Expense.and.Reimbursement.auth.entities;



import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;

    @NotBlank(message = "refresh token cannot be blank")
    @Column(nullable = false)
    private String refreshToken;

    @Column(nullable = false)
    private Instant expirationTime;

    @OneToOne
    private User user;

    public RefreshToken(Integer tokenId, @NotBlank(message = "refresh token cannot be blank") String refreshToken, Instant expirationTime, User user) {
        this.tokenId = tokenId;
        this.refreshToken = refreshToken;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    public RefreshToken() {
    }

    public static RefreshTokenBuilder builder() {
        return new RefreshTokenBuilder();
    }

    public Integer getTokenId() {
        return this.tokenId;
    }

    public @NotBlank(message = "refresh token cannot be blank") String getRefreshToken() {
        return this.refreshToken;
    }

    public Instant getExpirationTime() {
        return this.expirationTime;
    }

    public User getUser() {
        return this.user;
    }

    public static class RefreshTokenBuilder {
        private Integer tokenId;
        private @NotBlank(message = "refresh token cannot be blank") String refreshToken;
        private Instant expirationTime;
        private User user;

        RefreshTokenBuilder() {
        }

        public RefreshTokenBuilder tokenId(Integer tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public RefreshTokenBuilder refreshToken(@NotBlank(message = "refresh token cannot be blank") String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public RefreshTokenBuilder expirationTime(Instant expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public RefreshTokenBuilder user(User user) {
            this.user = user;
            return this;
        }

        public RefreshToken build() {
            return new RefreshToken(this.tokenId, this.refreshToken, this.expirationTime, this.user);
        }

        public String toString() {
            return "RefreshToken.RefreshTokenBuilder(tokenId=" + this.tokenId + ", refreshToken=" + this.refreshToken + ", expirationTime=" + this.expirationTime + ", user=" + this.user + ")";
        }
    }
}


