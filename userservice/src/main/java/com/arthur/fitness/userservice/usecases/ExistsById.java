package com.arthur.fitness.userservice.usecases;

import com.arthur.fitness.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExistsById {

    private final UserRepository userRepository;

    public boolean existsById(final String userId){
        return userRepository.existsById(userId);
    }
}
