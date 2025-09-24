package com.arthur.activityservice.controllers;

import com.arthur.activityservice.dtos.ActivityRequest;
import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.usecases.GetActivity;
import com.arthur.activityservice.usecases.GetUserActivities;
import com.arthur.activityservice.usecases.TrackActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final TrackActivity activityTrack;
    private final GetUserActivities getUserActivities;
    private final GetActivity getActivity;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request , @RequestHeader("X-User-ID") String userId){
        if(userId != null){
            request.setUserId(userId);
        }
        return ResponseEntity.ok(activityTrack.trackActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader("X-User-ID") final String userId){
        return ResponseEntity.ok(getUserActivities.getUserActivities(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable final String activityId){
        return ResponseEntity.ok(getActivity.getActivityById(activityId));
    }
}
