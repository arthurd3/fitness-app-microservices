package com.arthur.activityservice.usecases;

import com.arthur.activityservice.dtos.ActivityRequest;
import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.mappers.ActivityToResponse;
import com.arthur.activityservice.models.Activity;
import com.arthur.activityservice.reposiroty.ActivityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrackActivity {

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;


    public ActivityResponse trackActivity(final ActivityRequest request) {
        log.info("==> ACTIVITY-SERVICE: Chamando validação para o userId: '[{}]'", request.getUserId());
        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        log.info("user validation is {}", isValidUser);
        if (!isValidUser) {
            throw new RuntimeException("Invalid user id");
        }

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
