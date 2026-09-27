package com.merkit.service;

import com.merkit.entity.RefreshToken;
import com.merkit.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void revokeRefreshToken(String token);

    void revokeAllUserTokens(User user);

}