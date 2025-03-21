package com.gs.tj.controller;

import java.time.Duration;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.Route;
import com.gs.tj.service.RouteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling route-related operations.
 * Provides endpoints for route management, planning, and optimization.
 */
@RestController
@RequestMapping("/api/routes")
@RequiredArgsConstructor
@Tag(name = "Route Management", description = "APIs for managing travel routes")
public class RouteController {

    private final RouteService routeService;

    @PostMapping
    @Operation(summary = "Create a new route", description = "Creates a new travel route with the provided details")
    public ResponseEntity<Route> createRoute(@Valid @RequestBody Route route) {
        return ResponseEntity.ok(routeService.createRoute(route));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get route by ID", description = "Retrieves a specific route by its ID")
    public ResponseEntity<Route> getRouteById(
            @Parameter(description = "ID of the route to retrieve") @PathVariable Long id) {
        return routeService.getRouteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get routes by user", description = "Retrieves all routes created by a specific user")
    public ResponseEntity<List<Route>> getRoutesByUser(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        return ResponseEntity.ok(routeService.getRoutesByUser(userId));
    }

    @GetMapping("/start/{locationId}")
    @Operation(summary = "Get routes by start location", description = "Retrieves all routes starting from a specific location")
    public ResponseEntity<List<Route>> getRoutesByStartLocation(
            @Parameter(description = "ID of the start location") @PathVariable Long locationId) {
        return ResponseEntity.ok(routeService.getRoutesByStartLocation(locationId));
    }

    @GetMapping("/end/{locationId}")
    @Operation(summary = "Get routes by end location", description = "Retrieves all routes ending at a specific location")
    public ResponseEntity<List<Route>> getRoutesByEndLocation(
            @Parameter(description = "ID of the end location") @PathVariable Long locationId) {
        return ResponseEntity.ok(routeService.getRoutesByEndLocation(locationId));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update route", description = "Updates an existing route with new details")
    public ResponseEntity<Route> updateRoute(
            @Parameter(description = "ID of the route to update") @PathVariable Long id,
            @Valid @RequestBody Route routeDetails) {
        return ResponseEntity.ok(routeService.updateRoute(id, routeDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete route", description = "Deletes a specific route by its ID")
    public ResponseEntity<Void> deleteRoute(
            @Parameter(description = "ID of the route to delete") @PathVariable Long id) {
        routeService.deleteRoute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{routeId}/duration")
    @Operation(summary = "Calculate route duration", description = "Calculates the estimated duration of a specific route")
    public ResponseEntity<Duration> calculateRouteDuration(
            @Parameter(description = "ID of the route") @PathVariable Long routeId) {
        return ResponseEntity.ok(routeService.calculateRouteDuration(routeId));
    }

    @GetMapping("/optimal")
    @Operation(summary = "Find optimal routes", description = "Finds optimal routes between two locations with a maximum number of stops")
    public ResponseEntity<List<Route>> findOptimalRoutes(
            @Parameter(description = "ID of the start location") @RequestParam Long startLocationId,
            @Parameter(description = "ID of the end location") @RequestParam Long endLocationId,
            @Parameter(description = "Maximum number of stops") @RequestParam(defaultValue = "5") int maxStops) {
        return ResponseEntity.ok(routeService.findOptimalRoutes(startLocationId, endLocationId, maxStops));
    }

    @GetMapping("/popular")
    @Operation(summary = "Get popular routes", description = "Retrieves popular public routes")
    public ResponseEntity<List<Route>> getPopularRoutes(
            @Parameter(description = "Maximum number of routes to return") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(routeService.getPopularRoutes(limit));
    }

    @GetMapping("/recommended")
    @Operation(summary = "Get recommended routes", description = "Retrieves personalized route recommendations for a user")
    public ResponseEntity<List<Route>> getRecommendedRoutes(
            @Parameter(description = "ID of the user") @RequestParam Long userId,
            @Parameter(description = "Maximum number of routes to return") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(routeService.getRecommendedRoutes(userId, limit));
    }
} 