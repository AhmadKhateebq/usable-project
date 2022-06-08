package com.example.authserver.security.repository;

import com.example.authserver.security.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser,Integer> {
    MyUser getByUsername(String username);
    boolean existsByUsername(String username);
}
