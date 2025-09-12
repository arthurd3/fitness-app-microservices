package com.server.aiservice.usecases;

import com.server.aiservice.model.Recommendation;
import com.server.aiservice.repository.RecommendationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserRecommendation {

    private final RecommendationRepository recommendationRepository;

    public List<Recommendation> getUserRecommendation(final String userId) {
        return recommendationRepository.findByUserId(userId);
    }
}
