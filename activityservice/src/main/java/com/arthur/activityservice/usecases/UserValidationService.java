package com.arthur.activityservice.usecases;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserValidationService {

    private final WebClient userServiceWebClient;

    public boolean validateUser(final String userId) {
        try{
            log.info("Calling API Validating exist User by id {}", userId);
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("/api/v1/users/{userId}/validate", userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        }catch (WebClientResponseException ex){
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new RuntimeException("User not found" + userId);
            if(ex.getStatusCode() == HttpStatus.BAD_REQUEST)
                throw new RuntimeException("Invalid Request" + userId);
            return false;
        }
    }
}
