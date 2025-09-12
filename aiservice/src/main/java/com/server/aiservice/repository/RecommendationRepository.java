package com.server.aiservice.repository;

import com.server.aiservice.model.Recommendation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecommendationRepository extends MongoRepository<Recommendation , String>{
}
