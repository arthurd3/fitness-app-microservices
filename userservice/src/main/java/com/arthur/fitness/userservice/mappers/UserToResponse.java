package com.arthur.fitness.userservice.mappers;

import com.arthur.fitness.userservice.dtos.UserResponse;
import com.arthur.fitness.userservice.models.User;

public class UserToResponse {

    public static UserResponse userToResponse(final User user){
        return UserResponse.builder()
                .id(user.getId())
                .keycloakId(user.getKeycloakId())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();
    }

}
