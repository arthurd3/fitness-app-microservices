package com.arthur.activityservice.controllers;

import com.arthur.activityservice.dtos.ActivityRequest;
import com.arthur.activityservice.dtos.ActivityResponse;
import com.arthur.activityservice.usecases.TrackActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final TrackActivity activityTrack;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request){
        return ResponseEntity.ok(activityTrack.trackActivity(request));
    }
}
