package com.fitness.gateway.user.mappers;

import com.fitness.gateway.user.RegisterRequest;
import com.nimbusds.jwt.JWTClaimsSet;

import java.text.ParseException;

public class JWTtoRequest {

    public static RegisterRequest jwtToRequest(final JWTClaimsSet claims) throws ParseException {
        return RegisterRequest.builder()
                .email(claims.getStringClaim("email"))
                .keycloakId(claims.getStringClaim("sub"))
                .password("dummy@123123")
                .firstName(claims.getStringClaim("given_name"))
                .lastName(claims.getStringClaim("family_name"))
                .build();
    }
}
