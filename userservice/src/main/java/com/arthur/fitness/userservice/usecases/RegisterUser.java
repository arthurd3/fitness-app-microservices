package com.arthur.fitness.userservice.usecases;

import com.arthur.fitness.userservice.dtos.RegisterRequest;
import com.arthur.fitness.userservice.dtos.UserResponse;
import com.arthur.fitness.userservice.mappers.UserToResponse;
import com.arthur.fitness.userservice.models.User;
import com.arthur.fitness.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUser {

    private final UserRepository userRepository;

    public UserResponse register(final RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            User existingUser = userRepository.findByEmail(request.getEmail());
            return UserToResponse.userToResponse(existingUser);
        }

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .keycloakId(request.getKeycloakId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        var savedUser = userRepository.save(user);

        return UserToResponse.userToResponse(savedUser);
    }
}
