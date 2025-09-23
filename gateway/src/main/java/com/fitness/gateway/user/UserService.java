package com.fitness.gateway.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final WebClient userServiceWebClient;

    public Mono<Boolean> validateUser(final String userId) {
        log.info("Calling API Validating exist User by id {}", userId);
        return userServiceWebClient.get()
            .uri("/api/v1/users/{userId}/validate", userId)
            .retrieve()
            .bodyToMono(Boolean.class)
            .onErrorResume(WebClientResponseException.class , e -> {
                if(e.getStatusCode() == HttpStatus.NOT_FOUND)
                    return Mono.error(new RuntimeException("User not found" + userId));
                if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                    return Mono.error(new RuntimeException("Invalid Request" + userId));
                return Mono.error(new RuntimeException("Unexpected Error" + userId));
            });
    }

    public Mono<UserResponse> registerUser(final RegisterRequest request) {
        log.info("Calling User Registration API for email: {}", request.getEmail());
        return userServiceWebClient.post()
                .uri("/api/v1/users/register")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class , e -> {
                    if(e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Bad Request" + e.getMessage()));
                    if(e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                        return Mono.error(new RuntimeException("Internal Server Error" + e.getMessage()));
                    return Mono.error(new RuntimeException("Unexpected Error" + e.getMessage()));
                });
    }
}
