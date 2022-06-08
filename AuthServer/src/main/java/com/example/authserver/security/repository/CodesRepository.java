package com.example.authserver.security.repository;

import com.example.authserver.security.model.Codes;
import com.example.authserver.security.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodesRepository extends JpaRepository<Codes,Integer> {
    Codes findByCode(String code);
}
