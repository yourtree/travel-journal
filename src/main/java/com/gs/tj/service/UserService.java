package com.gs.tj.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.gs.tj.entity.User;

/**
 * Service interface for user management operations.
 * Handles user registration, authentication, and profile management.
 */
public interface UserService {
    /**
     * Register a new user.
     *
     * @param user The user to register
     * @return The registered user
     */
    User registerUser(User user);

    /**
     * Get a user by their ID.
     *
     * @param id The ID of the user
     * @return Optional containing the user if found
     */
    Optional<User> getUserById(Long id);

    /**
     * Get a user by their username.
     *
     * @param username The username
     * @return Optional containing the user if found
     */
    Optional<User> getUserByUsername(String username);

    /**
     * Get a user by their email.
     *
     * @param email The email address
     * @return Optional containing the user if found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Update user profile information.
     *
     * @param id The ID of the user to update
     * @param userDetails The updated user details
     * @return The updated user
     */
    User updateUser(Long id, User userDetails);

    /**
     * Delete a user.
     *
     * @param id The ID of the user to delete
     */
    void deleteUser(Long id);

    /**
     * Change user password.
     *
     * @param id The ID of the user
     * @param oldPassword The current password
     * @param newPassword The new password
     * @return The updated user
     */
    User changePassword(Long id, String oldPassword, String newPassword);

    /**
     * Reset user password.
     *
     * @param email The email address of the user
     * @return true if password reset email was sent
     */
    boolean resetPassword(String email);

    /**
     * Update user profile image.
     *
     * @param id The ID of the user
     * @param imageUrl The URL of the new profile image
     * @return The updated user
     */
    User updateProfileImage(Long id, String imageUrl);

    /**
     * Get all users.
     *
     * @return List of all users
     */
    List<User> getAllUsers();

    /**
     * Search users by username or email.
     *
     * @param query The search query
     * @return List of matching users
     */
    List<User> searchUsers(String query);

    /**
     * Follow a user.
     *
     * @param followerId The ID of the user following
     * @param followedId The ID of the user being followed
     * @return The updated follower user
     */
    User followUser(Long followerId, Long followedId);

    /**
     * Unfollow a user.
     *
     * @param followerId The ID of the user unfollowing
     * @param followedId The ID of the user being unfollowed
     * @return The updated follower user
     */
    User unfollowUser(Long followerId, Long followedId);

    /**
     * Get user's followers.
     *
     * @param userId The ID of the user
     * @return List of followers
     */
    List<User> getFollowers(Long userId);

    /**
     * Get users that a user is following.
     *
     * @param userId The ID of the user
     * @return List of users being followed
     */
    List<User> getFollowing(Long userId);

    /**
     * Check if a username exists.
     *
     * @param username The username to check
     * @return true if the username exists
     */
    boolean existsByUsername(String username);

    /**
     * Check if an email exists.
     *
     * @param email The email to check
     * @return true if the email exists
     */
    boolean existsByEmail(String email);

    /**
     * Load user by username for Spring Security.
     *
     * @param username The username
     * @return UserDetails object
     * @throws UsernameNotFoundException if user not found
     */
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
} 