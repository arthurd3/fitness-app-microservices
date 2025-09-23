package com.fitness.gateway.user.mappers;


import com.fitness.gateway.user.RegisterRequest;
import com.fitness.gateway.user.UserResponse;

public class UserToResponse {

    public static UserResponse userToResponse(final RegisterRequest user){
        return UserResponse.builder()
                .keycloakId(user.getKeycloakId())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .build();
    }

}
