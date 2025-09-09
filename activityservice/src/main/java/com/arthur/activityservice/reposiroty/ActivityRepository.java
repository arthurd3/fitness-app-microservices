package com.arthur.activityservice.reposiroty;

import com.arthur.activityservice.models.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {
}
