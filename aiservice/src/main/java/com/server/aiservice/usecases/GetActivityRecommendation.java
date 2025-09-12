package com.server.aiservice.usecases;

import com.server.aiservice.model.Recommendation;
import com.server.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetActivityRecommendation {

    private final RecommendationRepository recommendationRepository;

    public Recommendation getActivityRecommendation(final String activityId) {
        return recommendationRepository.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("No recommendation found for activityId:" + activityId));
    }
}
