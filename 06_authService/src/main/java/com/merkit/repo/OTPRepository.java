package com.merkit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.OTP;

public interface OTPRepository extends JpaRepository<OTP, Long> {

    Optional<OTP> findFirstByEmailOrderByIdDesc(String email);

    Optional<OTP> findFirstByMobileOrderByIdDesc(Long mobile);
}