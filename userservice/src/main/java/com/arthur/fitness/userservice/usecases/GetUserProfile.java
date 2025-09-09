package com.arthur.fitness.userservice.usecases;

import com.arthur.fitness.userservice.dtos.UserResponse;
import com.arthur.fitness.userservice.mappers.UserToResponse;
import com.arthur.fitness.userservice.models.User;
import com.arthur.fitness.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserProfile {

    private final UserRepository userRepository;

    public UserResponse getUserProfile(final String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return UserToResponse.userToResponse(user);
    }
}
