package com.example.demo.infraestructure.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class AuthenticationUtil {

    private static final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public static boolean verifyPassword(String password,
                                         String hashedPassword) {
        return passwordEncoder.matches(password, hashedPassword);
    }
}
