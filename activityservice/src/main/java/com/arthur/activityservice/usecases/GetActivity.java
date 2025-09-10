package com.arthur.activityservice.usecases;

import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.mappers.ActivityToResponse;
import com.arthur.activityservice.reposiroty.ActivityRepository;
import org.springframework.stereotype.Service;

@Service
public class GetActivity {
    
    private final ActivityRepository activityRepository;

    public GetActivity(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public ActivityResponse getActivityById(final String activityId) {
        return activityRepository.findById(activityId)
                .map(ActivityToResponse::activityToResponse)
                .orElseThrow(() -> new RuntimeException("Activity not found with id: " + activityId));
    }
}
