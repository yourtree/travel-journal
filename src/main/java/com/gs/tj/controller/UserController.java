package com.gs.tj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.User;
import com.gs.tj.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * REST controller for User management.
 * Provides endpoints for user CRUD operations and profile management.
 */
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> registerUser(@Valid @RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Get all users (Admin only)")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user profile")
    public ResponseEntity<User> updateUser(
            @Parameter(description = "User ID") @PathVariable Long id,
            @Valid @RequestBody User userDetails) {
        return ResponseEntity.ok(userService.updateUser(id, userDetails));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete user (Admin only)")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "User ID") @PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/password")
    @Operation(summary = "Change user password")
    public ResponseEntity<User> changePassword(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {
        return ResponseEntity.ok(userService.changePassword(id, oldPassword, newPassword));
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset user password")
    public ResponseEntity<Boolean> resetPassword(
            @Parameter(description = "User email") @RequestParam String email) {
        return ResponseEntity.ok(userService.resetPassword(email));
    }

    @PutMapping("/{id}/profile-image")
    @Operation(summary = "Update user profile image")
    public ResponseEntity<User> updateProfileImage(
            @Parameter(description = "User ID") @PathVariable Long id,
            @RequestParam String imageUrl) {
        return ResponseEntity.ok(userService.updateProfileImage(id, imageUrl));
    }

    @GetMapping("/search")
    @Operation(summary = "Search users by username or email")
    public ResponseEntity<List<User>> searchUsers(
            @Parameter(description = "Search query") @RequestParam String query) {
        return ResponseEntity.ok(userService.searchUsers(query));
    }

    @PostMapping("/{followerId}/follow/{followedId}")
    @Operation(summary = "Follow a user")
    public ResponseEntity<User> followUser(
            @Parameter(description = "ID of the user following") @PathVariable Long followerId,
            @Parameter(description = "ID of the user being followed") @PathVariable Long followedId) {
        return ResponseEntity.ok(userService.followUser(followerId, followedId));
    }

    @DeleteMapping("/{followerId}/follow/{followedId}")
    @Operation(summary = "Unfollow a user")
    public ResponseEntity<User> unfollowUser(
            @Parameter(description = "ID of the user unfollowing") @PathVariable Long followerId,
            @Parameter(description = "ID of the user being unfollowed") @PathVariable Long followedId) {
        return ResponseEntity.ok(userService.unfollowUser(followerId, followedId));
    }

    @GetMapping("/{userId}/followers")
    @Operation(summary = "Get user's followers")
    public ResponseEntity<List<User>> getFollowers(
            @Parameter(description = "User ID") @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowers(userId));
    }

    @GetMapping("/{userId}/following")
    @Operation(summary = "Get users that a user is following")
    public ResponseEntity<List<User>> getFollowing(
            @Parameter(description = "User ID") @PathVariable Long userId) {
        return ResponseEntity.ok(userService.getFollowing(userId));
    }
} 