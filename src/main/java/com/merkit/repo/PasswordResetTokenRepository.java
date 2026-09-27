package com.merkit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.PasswordResetToken;
import com.merkit.entity.User;

public interface PasswordResetTokenRepository
        extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByToken(String token);

    void deleteByUser(User user);
    void deleteByToken(String token);
}


