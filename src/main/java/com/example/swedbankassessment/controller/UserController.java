package com.example.swedbankassessment.controller;

import com.example.swedbankassessment.entity.ApplicationUser;
import com.example.swedbankassessment.model.UserRequest;
import com.example.swedbankassessment.service.ApplicationUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {

    private ApplicationUserService applicationUserService;

    @PostMapping(path = "/")
    public ResponseEntity<UserRequest> createApplicationUser(@RequestBody @Valid UserRequest userRequest) {
        ApplicationUser applicationUser = applicationUserService.saveApplicationUser(userRequest);
        userRequest.setId(applicationUser.getId());
        return ResponseEntity.ok().body(userRequest);
    }

    @PutMapping(path = "/")
    public ResponseEntity<UserRequest> updateApplicationUser(@RequestBody @Valid UserRequest userRequest) {
        return ResponseEntity.ok().body(applicationUserService.updateApplicationUser(userRequest));
    }

    @GetMapping(path = "/{applicationUserId}")
    public ResponseEntity<UserRequest> getApplicationUser(@PathVariable Long applicationUserId) {
        return ResponseEntity.ok().body(applicationUserService.findApplicationUserByIdUserModel(applicationUserId));
    }

    @GetMapping(path = "/")
    public ResponseEntity<List> getAllApplicationUser() {
        return ResponseEntity.ok().body(applicationUserService.findAllApplicationUser());
    }

    @DeleteMapping(path = "/{applicationUserId}")
    public ResponseEntity deleteApplicationUser(@PathVariable Long applicationUserId) {
        applicationUserService.deleteApplicationUser(applicationUserId);
        return ResponseEntity.ok().body("User "+applicationUserId+" deleted successfully");
    }
}
