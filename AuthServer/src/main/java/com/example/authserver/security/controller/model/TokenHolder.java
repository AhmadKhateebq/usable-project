package com.example.authserver.security.controller.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public
class TokenHolder extends AuthUser {
    String token;
}
