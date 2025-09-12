package com.server.aiservice.controller;

import com.server.aiservice.model.Recommendation;
import com.server.aiservice.repository.RecommendationRepository;
import com.server.aiservice.usecases.GetActivityRecommendation;
import com.server.aiservice.usecases.GetUserRecommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {

    private final GetUserRecommendation userRecommendation;
    private final GetActivityRecommendation activityRecommendation;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendations(@PathVariable final String userId) {
        return ResponseEntity.ok(userRecommendation.getUserRecommendation(userId));
    }


    @GetMapping("/user/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendations(@PathVariable final String activityId) {
        return ResponseEntity.ok(activityRecommendation.getActivityRecommendation(activityId));
    }
}
