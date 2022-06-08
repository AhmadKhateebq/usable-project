package com.example.redirectserver.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public
class AuthUser {
    private String username;
    private String password;

}
