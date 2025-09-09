package com.arthur.activityservice.usecases;

import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.mappers.ActivityToResponse;
import com.arthur.activityservice.models.Activity;
import com.arthur.activityservice.reposiroty.ActivityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetUserActivities {

    private final ActivityRepository activityRepository;

    public GetUserActivities(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<ActivityResponse> getUserActivities(final String userId) {
        List<Activity> activities = activityRepository.findByUserId(userId);

        return activities.stream()
                .map(ActivityToResponse::activityToResponse)
                .collect(Collectors.toList());
    }
}
