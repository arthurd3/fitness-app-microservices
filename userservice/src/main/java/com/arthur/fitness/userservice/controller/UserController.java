package com.arthur.fitness.userservice.controller;

import com.arthur.fitness.userservice.dto.RegisterRequest;
import com.arthur.fitness.userservice.dto.UserResponse;
import com.arthur.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable final String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @GetMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody final RegisterRequest request){
        return ResponseEntity.ok(request.register(request));
    }

}
