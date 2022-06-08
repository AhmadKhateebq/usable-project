package com.example.authserver.security.service.util;

import java.util.List;

public class PassMatcher {
    private static final List<String> list = List.of (
            "123",
            "1234",
            "12345",
            "123456",
            "1234567",
            "12345678",
            "123456789",
            "012",
            "0123",
            "01234",
            "012345",
            "0123456",
            "01234567",
            "012345678",
            "0123456789",
            "qwerty",
            "password",
            "12345678",
            "111111",
            "123123",
            "1234567890",
            "1234567",
            "qwerty123",
            "000000",
            "1q2w3e",
            "aa12345678",
            "abc123",
            "password1",
            "1234",
            "qwertyuiop",
            "123321",
            "password123"
    );
    public static boolean validate(String passoword){
        for (String s : list) {
            if (s.contains (passoword))
                return false;
        }
        return true;
    }
}
