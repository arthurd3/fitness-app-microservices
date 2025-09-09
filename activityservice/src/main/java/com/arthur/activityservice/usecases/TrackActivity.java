package com.arthur.activityservice.usecases;

import com.arthur.activityservice.dtos.ActivityRequest;
import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.mappers.ActivityToResponse;
import com.arthur.activityservice.models.Activity;
import com.arthur.activityservice.reposiroty.ActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrackActivity {

    private final ActivityRepository activityRepository;

    public ActivityResponse trackActivity(final ActivityRequest request) {

        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getActivityType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        var savedActivity = activityRepository.save(activity);

        return ActivityToResponse
                .activityToResponse(savedActivity);
    }


}
