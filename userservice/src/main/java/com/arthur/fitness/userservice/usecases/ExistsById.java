package com.arthur.fitness.userservice.usecases;

import com.arthur.fitness.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ExistsById {

    private final UserRepository userRepository;

    public boolean existsUserById(final String userId){
        log.info("Validating exist User by id {}", userId);
        return userRepository.existsByKeycloakId(userId);
    }
}
