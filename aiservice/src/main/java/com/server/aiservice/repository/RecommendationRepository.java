package com.server.aiservice.repository;

import com.server.aiservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends MongoRepository<Recommendation , String>{
    List<Recommendation> findByUserId(final String userId);

    Optional<Recommendation> findByActivityId(final String activityId);
}
