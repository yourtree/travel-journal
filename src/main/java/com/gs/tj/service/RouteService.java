package com.gs.tj.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import com.gs.tj.entity.Route;

/**
 * Service interface for route planning and management.
 * Handles CRUD operations and route-related calculations.
 */
public interface RouteService {
    Route createRoute(Route route);
    Optional<Route> getRouteById(Long id);
    List<Route> getRoutesByUser(Long userId);
    List<Route> getRoutesByStartLocation(Long locationId);
    List<Route> getRoutesByEndLocation(Long locationId);
    Route updateRoute(Long id, Route routeDetails);
    void deleteRoute(Long id);
    Duration calculateRouteDuration(Long routeId);
    List<Route> findOptimalRoutes(Long startLocationId, Long endLocationId, int maxStops);
    List<Route> getPopularRoutes(int limit);
    List<Route> getRecommendedRoutes(Long userId, int limit);
} 