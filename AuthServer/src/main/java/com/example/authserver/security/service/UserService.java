package com.example.authserver.security.service;

import com.example.authserver.security.controller.model.AuthUser;
import com.example.authserver.security.controller.model.TokenHolder;
import com.example.authserver.security.model.Codes;
import com.example.authserver.security.model.MyUser;
import com.example.authserver.security.repository.CodesRepository;
import com.example.authserver.security.repository.UserRepository;
import com.example.authserver.security.service.util.PassMatcher;
import com.example.authserver.security.util.jwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    jwtUtil util;
    @Autowired
    UserRepository repository;
    @Autowired
    private CodesRepository codesRepository;


    private void saveUser(MyUser user){
       repository.save (user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance ();
    }

    public boolean isUserValid(String username, String password) {
        try {
            return repository.getByUsername (username).getPassword ().equals (password);
        } catch (Exception e) {
            return false;
        }
    }

    public String getToken(AuthUser authUser) {
        if (isUserValid (authUser.getUsername (), authUser.getPassword ()))
            return util.generateToken (authUser.toUser ());
        else
            return ("null");
    }

    public boolean validateToken(TokenHolder authUser) {
        return util.validateToken (authUser.getToken (), authUser.toUser ());
    }

    public boolean validateToken(String token) {
        return util.validateToken (token);
    }

    public Claims extractAllClaims(String token) {
        return util.extractAllClaims (token);
    }

    public String login(AuthUser user) {
        return isUserValid (
                user.getUsername (), user.getPassword ()) ?
                getToken (user) : "Credential Not Presented";
    }

    public String login(String username,String token){
        try {
            MyUser user = repository.getByUsername (username);
            Codes code = codesRepository.findByCode (token);
            if(code.getUser ().equals (user) && !code.isUsed ()){
                return login (user.getUsername (),user.getPassword ());
            }
        }catch (Exception e){
            e.printStackTrace ();
            return "error while using this code";
        }
        return "error while using this code";
    }
    public int signup(AuthUser user) {
        String password = user.getPassword ();
        if (password.length () < 8 )
            return -1;
        if (password.length ()>15)
            return -2;
        if (password.equals (user.getUsername ()))
            return -3;
        if (PassMatcher.validate (password)) {
            MyUser myUser = new MyUser ();
            myUser.setUsername (user.getUsername ());
            myUser.setPassword (user.getPassword ());
            saveUser (myUser);
            MyUser newUser = repository.getByUsername (user.getUsername ());
            for (int i = 0; i < 5; i++) {
                Codes code = new Codes ();
                code.setCode (getCode ());
                code.setUser (newUser);
                code.setUsed (false);
                codesRepository.save (code);
            }
            return 1;
        }
        return -3;
    }
    private static String getCode() {
        StringBuilder sb = new StringBuilder ();
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        sb.append ((Character.toChars ((int) (33+(93*Math.random ())))));
        return sb.toString ();
    }
}
