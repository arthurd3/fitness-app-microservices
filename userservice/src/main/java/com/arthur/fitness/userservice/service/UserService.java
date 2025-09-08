package com.arthur.fitness.userservice.service;

import com.arthur.fitness.userservice.dto.RegisterRequest;
import com.arthur.fitness.userservice.dto.UserResponse;
import com.arthur.fitness.userservice.model.User;
import com.arthur.fitness.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse register(final RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .build();

        var savedUser = userRepository.save(user);

        return userToResponse(savedUser);
    }

    public UserResponse getUserProfile(final String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userToResponse(user);
    }

    private UserResponse userToResponse(final User user){

        return UserResponse.builder()
                .id(user.getId())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .updatedAt(user.getUpdatedAt())
                .createdAt(user.getCreatedAt())
                .build();
    }
}
