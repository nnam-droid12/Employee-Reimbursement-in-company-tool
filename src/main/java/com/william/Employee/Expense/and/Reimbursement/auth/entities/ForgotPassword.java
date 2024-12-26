package com.william.Employee.Expense.and.Reimbursement.auth.entities;




import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fpId;

    @Column(nullable = false)
    private Integer otp;

    @Column(nullable = false)
    private Date expirationTime;

    @OneToOne
    private User user;

    public ForgotPassword(Integer fpId, Integer otp, Date expirationTime, User user) {
        this.fpId = fpId;
        this.otp = otp;
        this.expirationTime = expirationTime;
        this.user = user;
    }

    public ForgotPassword() {
    }

    public static ForgotPasswordBuilder builder() {
        return new ForgotPasswordBuilder();
    }

    public Integer getFpId() {
        return this.fpId;
    }

    public Integer getOtp() {
        return this.otp;
    }

    public Date getExpirationTime() {
        return this.expirationTime;
    }

    public User getUser() {
        return this.user;
    }

    public static class ForgotPasswordBuilder {
        private Integer fpId;
        private Integer otp;
        private Date expirationTime;
        private User user;

        ForgotPasswordBuilder() {
        }

        public ForgotPasswordBuilder fpId(Integer fpId) {
            this.fpId = fpId;
            return this;
        }

        public ForgotPasswordBuilder otp(Integer otp) {
            this.otp = otp;
            return this;
        }

        public ForgotPasswordBuilder expirationTime(Date expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        public ForgotPasswordBuilder user(User user) {
            this.user = user;
            return this;
        }

        public ForgotPassword build() {
            return new ForgotPassword(this.fpId, this.otp, this.expirationTime, this.user);
        }

        public String toString() {
            return "ForgotPassword.ForgotPasswordBuilder(fpId=" + this.fpId + ", otp=" + this.otp + ", expirationTime=" + this.expirationTime + ", user=" + this.user + ")";
        }
    }
}

