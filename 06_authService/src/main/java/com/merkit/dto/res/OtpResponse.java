package com.merkit.dto.res;

public record OtpResponse(
        boolean sent,
        String message
) {}
