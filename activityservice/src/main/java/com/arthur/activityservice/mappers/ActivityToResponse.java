package com.arthur.activityservice.mappers;

import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.models.Activity;

public class ActivityToResponse {

    public static ActivityResponse activityToResponse(final Activity activity) {
        return ActivityResponse.builder()
                .id(activity.getId())
                .userId(activity.getUserId())
                .type(activity.getType())
                .duration(activity.getDuration())
                .caloriesBurned(activity.getCaloriesBurned())
                .startTime(activity.getStartTime())
                .additionalMetrics(activity.getAdditionalMetrics())
                .createdAt(activity.getCreatedAt())
                .updatedAt(activity.getUpdatedAt())
                .build();
    }
}
