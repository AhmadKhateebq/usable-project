package com.example.redirectserver.controller;

import com.example.redirectserver.controller.model.AuthUser;
import com.example.redirectserver.util.Client;
import com.example.redirectserver.util.URLS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    Client client;
    @GetMapping("/login")
    ResponseEntity<String> login(@RequestBody AuthUser authUser){
        return client.exchangeWithBody (URLS.GET_TOKEN,authUser);
    }
    @GetMapping("/validate")
    ResponseEntity<String> validateToken(@RequestBody String token){
        return  client.exchangeWithBody (URLS.IS_TOKEN_VALID,token);
    }



}
