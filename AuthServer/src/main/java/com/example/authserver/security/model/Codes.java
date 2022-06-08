package com.example.authserver.security.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_codes")
public class Codes {
    @Id
            @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @ManyToOne
            @JoinColumn(name = "user_id",referencedColumnName = "id")
    MyUser user;
    String code;
    boolean isUsed;

}
