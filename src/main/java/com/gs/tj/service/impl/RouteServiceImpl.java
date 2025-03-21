package com.gs.tj.service.impl;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.tj.entity.Route;
import com.gs.tj.exception.ResourceNotFoundException;
import com.gs.tj.repository.RouteRepository;
import com.gs.tj.service.RouteService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of RouteService interface.
 * Provides route management functionality with caching support.
 */
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;

    @Override
    @Transactional
    @CacheEvict(value = "routes", allEntries = true)
    public Route createRoute(Route route) {
        return routeRepository.save(route);
    }

    @Override
    @Cacheable(value = "routes", key = "#id")
    public Optional<Route> getRouteById(Long id) {
        return routeRepository.findById(id);
    }

    @Override
    @Cacheable(value = "routes", key = "'user:' + #userId")
    public List<Route> getRoutesByUser(Long userId) {
        return routeRepository.findByUserId(userId);
    }

    @Override
    @Cacheable(value = "routes", key = "'start:' + #locationId")
    public List<Route> getRoutesByStartLocation(Long locationId) {
        return routeRepository.findByStartLocationId(locationId);
    }

    @Override
    @Cacheable(value = "routes", key = "'end:' + #locationId")
    public List<Route> getRoutesByEndLocation(Long locationId) {
        return routeRepository.findByEndLocationId(locationId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "routes", key = "#id")
    public Route updateRoute(Long id, Route routeDetails) {
        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + id));

        if (routeDetails.getName() != null) {
            route.setName(routeDetails.getName());
        }
        if (routeDetails.getStartLocation() != null) {
            route.setStartLocation(routeDetails.getStartLocation());
        }
        if (routeDetails.getEndLocation() != null) {
            route.setEndLocation(routeDetails.getEndLocation());
        }
        if (routeDetails.getEstimatedDuration() != null) {
            route.setEstimatedDuration(routeDetails.getEstimatedDuration());
        }
        if (routeDetails.getStopLocationIds() != null) {
            route.setStopLocationIds(routeDetails.getStopLocationIds());
        }
        if (routeDetails.getDescription() != null) {
            route.setDescription(routeDetails.getDescription());
        }
        if (routeDetails.getImageUrls() != null) {
            route.setImageUrls(routeDetails.getImageUrls());
        }
        route.setPublic(routeDetails.isPublic());

        return routeRepository.save(route);
    }

    @Override
    @Transactional
    @CacheEvict(value = "routes", key = "#id")
    public void deleteRoute(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Route not found with id: " + id);
        }
        routeRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "routeDurations", key = "#routeId")
    public Duration calculateRouteDuration(Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found with id: " + routeId));
        return route.getEstimatedDuration();
    }

    @Override
    @Cacheable(value = "optimalRoutes", key = "#startLocationId + ':' + #endLocationId + ':' + #maxStops")
    public List<Route> findOptimalRoutes(Long startLocationId, Long endLocationId, int maxStops) {
        // TODO: Implement route optimization algorithm
        return List.of();
    }

    @Override
    @Cacheable(value = "popularRoutes", key = "#limit")
    public List<Route> getPopularRoutes(int limit) {
        return routeRepository.findByIsPublicTrueOrderByCreatedAtDesc()
                .stream()
                .limit(limit)
                .toList();
    }

    @Override
    @Cacheable(value = "recommendedRoutes", key = "#userId + ':' + #limit")
    public List<Route> getRecommendedRoutes(Long userId, int limit) {
        // TODO: Implement route recommendation algorithm based on user preferences
        return List.of();
    }
} 