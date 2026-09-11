package com.merkit.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer otp;

    private String email;

    private Long mobile;

    private LocalDateTime exp;

    public OTP(Integer otp, String email, Long sec) {
        this.otp = otp;
        this.email = email;
        addSec(sec);
    }

    public OTP(Integer otp, Long mobile, Long sec) {
        this.otp = otp;
        this.mobile = mobile;
        addSec(sec);
    }

    private void addSec(Long sec) {
        this.exp = LocalDateTime.now().plusSeconds(sec);
    }

    public boolean valid() {
        return exp != null && exp.isAfter(LocalDateTime.now());
    }
}