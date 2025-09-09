package com.arthur.fitness.userservice.controllers;

import com.arthur.fitness.userservice.dtos.RegisterRequest;
import com.arthur.fitness.userservice.dtos.UserResponse;
import com.arthur.fitness.userservice.usecases.GetUserProfile;
import com.arthur.fitness.userservice.usecases.RegisterUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final GetUserProfile getUserProfile;
    private final RegisterUser registerUser;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable final String userId){
        return ResponseEntity.ok(getUserProfile.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody final RegisterRequest request){
        return ResponseEntity.ok(registerUser.register(request));
    }

}
