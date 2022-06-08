package com.example.authserver.security.controller;

import com.example.authserver.security.controller.model.AuthUser;
import com.example.authserver.security.controller.model.TokenHolder;
import com.example.authserver.security.service.JwtService;
import com.example.authserver.security.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {
    @Autowired
    JwtService jwtService;

    @Autowired
    UserService service;
    @GetMapping("/user")
    UserDetails getUser() {
        return jwtService.loadUserByUsername ("user");
    }

    @GetMapping("/getToken")
    ResponseEntity<String> getToken(@RequestBody AuthUser authUser) {
        return ResponseEntity.ok(service.getToken(authUser));
    }

    @GetMapping("/validate")
    boolean isValid(@RequestBody TokenHolder authUser) {
        return service.validateToken(authUser);
    }

    @GetMapping("/isValid")
    ResponseEntity<Boolean> isValid(@RequestBody String token) {
        try {
            return ResponseEntity.ok (service.validateToken (token));
        } catch (SignatureException | MalformedJwtException | ExpiredJwtException signatureException) {
            System.out.println (signatureException.getMessage ());
            return ResponseEntity.ok (false);
        }
    }

    @GetMapping("/claims")
    Claims getClaims(@RequestBody String token) {
        return service.extractAllClaims (token);
    }
    @GetMapping("login")
    String login(@RequestBody AuthUser user){
        return service.login(user);
    }

    @PostMapping("/signup")
    String signup(@RequestBody AuthUser user){
        return switch (service.signup (user)){
            case -1 -> "short password";
            case -2 -> "long password";
            case -3 -> "weak password";
            case 1 -> service.getToken (user);
            default -> throw new IllegalStateException ("Unexpected value: " + service.signup (user));
        };
    }

    @GetMapping("/login/c")
    String login(@RequestBody Tokener tokener){
        return service.login (tokener.username,tokener.code);
    }

}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Tokener{
    String username;
    String code;
}

