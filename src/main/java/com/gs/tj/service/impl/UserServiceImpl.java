package com.gs.tj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.tj.entity.User;
import com.gs.tj.exception.ResourceNotFoundException;
import com.gs.tj.repository.UserRepository;
import com.gs.tj.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of UserService interface.
 * Provides user management functionality with caching support.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    @CacheEvict(value = "users", allEntries = true)
    public User registerUser(User user) {
        if (existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Cacheable(value = "users", key = "'username:' + #username")
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Cacheable(value = "users", key = "'email:' + #email")
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Cacheable(value = "users", key = "'all'")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (userDetails.getFullName() != null) {
            user.setFullName(userDetails.getFullName());
        }
        if (userDetails.getBio() != null) {
            user.setBio(userDetails.getBio());
        }
        if (userDetails.getProfileImageUrl() != null) {
            user.setProfileImageUrl(userDetails.getProfileImageUrl());
        }
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }

        return userRepository.save(user);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public User changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new IllegalArgumentException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public boolean resetPassword(String email) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    // TODO: Implement password reset email sending
                    return true;
                })
                .orElse(false);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#id")
    public User updateProfileImage(Long id, String imageUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setProfileImageUrl(imageUrl);
        return userRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "'search:' + #query")
    public List<User> searchUsers(String query) {
        return userRepository.findByUsernameContainingIgnoreCaseOrEmailContainingIgnoreCase(query, query);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#followerId")
    public User followUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Follower not found with id: " + followerId));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("Followed user not found with id: " + followedId));

        follower.getFollowing().add(followed);
        return userRepository.save(follower);
    }

    @Override
    @Transactional
    @CacheEvict(value = "users", key = "#followerId")
    public User unfollowUser(Long followerId, Long followedId) {
        User follower = userRepository.findById(followerId)
                .orElseThrow(() -> new ResourceNotFoundException("Follower not found with id: " + followerId));
        User followed = userRepository.findById(followedId)
                .orElseThrow(() -> new ResourceNotFoundException("Followed user not found with id: " + followedId));

        follower.getFollowing().remove(followed);
        return userRepository.save(follower);
    }

    @Override
    @Cacheable(value = "users", key = "'followers:' + #userId")
    public List<User> getFollowers(Long userId) {
        return userRepository.findByFollowingId(userId);
    }

    @Override
    @Cacheable(value = "users", key = "'following:' + #userId")
    public List<User> getFollowing(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        return user.getFollowing().stream().toList();
    }
} 