package com.example.redirectserver.service;

import com.example.redirectserver.util.Client;
import com.example.redirectserver.util.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    Client client;

    private ResponseEntity validate(String token) {
        return client.exchangeWithBody (URLS.IS_TOKEN_VALID, token);
    }

    public int validate(Map<String, String> headers) {
        Optional<String> token = Optional.ofNullable (headers.get ("authorization"));
        if (token.isPresent ()) {
            if (validate (token.get ().split (" ")[1]).getBody ().equals ("true")) {
                return 200;
            } else
                return 401;
        }
        return 403;
    }
}
