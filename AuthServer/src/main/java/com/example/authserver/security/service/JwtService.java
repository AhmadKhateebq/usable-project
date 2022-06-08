package com.example.authserver.security.service;

import com.example.authserver.security.controller.model.AuthUser;
import com.example.authserver.security.controller.model.TokenHolder;
import com.example.authserver.security.model.MyUser;
import com.example.authserver.security.repository.UserRepository;
import com.example.authserver.security.util.jwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    UserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user = repository.getByUsername (username);
        return user.toUser ();
    }
    public boolean userNameExists(String user) {
        return repository.existsByUsername (user);
    }

}
