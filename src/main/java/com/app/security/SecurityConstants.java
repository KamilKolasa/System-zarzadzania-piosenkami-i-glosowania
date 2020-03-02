package com.app.security;

public interface SecurityConstants {
    String SECRET = "ExamSecurityKey";
    long EXPIRATION_TIME_ACCESS_TOKEN = 120_000L;
    long EXPIRATION_TIME_REFRESH_TOKEN = 120_000_000L;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING_ACCESS_TOKEN = "Authorization";
}
