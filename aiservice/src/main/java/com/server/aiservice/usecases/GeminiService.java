package com.server.aiservice.usecases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@Slf4j
public class GeminiService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private String geminiApiKey;



    private final WebClient webClient;

    public GeminiService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String getAnswer(final String question) {
        Map<String , Object> requestBody = Map.of(
                "contents", new Object[]{
                        Map.of("parts" , new Object[]{
                                Map.of("text", question)
                        })
                }
        );

        return webClient.post()
                .uri(geminiApiUrl + geminiApiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
