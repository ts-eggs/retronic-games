package com.retronic.persistence.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;

public class PasswordEncoder {

    private PasswordEncoder() {
        throw new IllegalStateException("Utility class");
    }

    public static String encodePassword(String password, String salt) {
        return Hashing.sha256().hashString(salt + password, Charsets.UTF_8).toString();
    }
}
