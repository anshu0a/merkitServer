package com.merkit.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.merkit.entity.RefreshToken;
import com.merkit.entity.User;


public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    List<RefreshToken> findAllByUser(User user);
}

