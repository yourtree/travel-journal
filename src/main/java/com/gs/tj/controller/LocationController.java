package com.gs.tj.controller;

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

import com.gs.tj.entity.Location;
import com.gs.tj.service.LocationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling location management operations.
 * Provides endpoints for CRUD operations and location-based queries.
 */
@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
@Tag(name = "Location Management", description = "APIs for managing travel locations")
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    @Operation(summary = "Create a new location")
    public ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) {
        return ResponseEntity.ok(locationService.createLocation(location));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get location by ID")
    public ResponseEntity<Location> getLocationById(
            @Parameter(description = "Location ID") @PathVariable Long id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get all locations")
    public ResponseEntity<List<Location>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/country/{country}")
    @Operation(summary = "Get locations by country")
    public ResponseEntity<List<Location>> getLocationsByCountry(
            @Parameter(description = "Country name") @PathVariable String country) {
        return ResponseEntity.ok(locationService.getLocationsByCountry(country));
    }

    @GetMapping("/city/{city}")
    @Operation(summary = "Get locations by city")
    public ResponseEntity<List<Location>> getLocationsByCity(
            @Parameter(description = "City name") @PathVariable String city) {
        return ResponseEntity.ok(locationService.getLocationsByCity(city));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update location")
    public ResponseEntity<Location> updateLocation(
            @Parameter(description = "Location ID") @PathVariable Long id,
            @Valid @RequestBody Location locationDetails) {
        return ResponseEntity.ok(locationService.updateLocation(id, locationDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete location")
    public ResponseEntity<Void> deleteLocation(
            @Parameter(description = "Location ID") @PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search locations")
    public ResponseEntity<List<Location>> searchLocations(
            @Parameter(description = "Search query") @RequestParam String query) {
        return ResponseEntity.ok(locationService.searchLocations(query));
    }

    @GetMapping("/popular")
    @Operation(summary = "Get popular locations")
    public ResponseEntity<List<Location>> getPopularLocations(
            @Parameter(description = "Maximum number of locations to return") 
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(locationService.getPopularLocations(limit));
    }

    @GetMapping("/nearby")
    @Operation(summary = "Get nearby locations")
    public ResponseEntity<List<Location>> getNearbyLocations(
            @Parameter(description = "Latitude") @RequestParam double latitude,
            @Parameter(description = "Longitude") @RequestParam double longitude,
            @Parameter(description = "Radius in meters") @RequestParam double radiusInMeters) {
        return ResponseEntity.ok(locationService.getNearbyLocations(latitude, longitude, radiusInMeters));
    }

    @PostMapping("/{id}/images")
    @Operation(summary = "Add image to location")
    public ResponseEntity<Location> addLocationImage(
            @Parameter(description = "Location ID") @PathVariable Long id,
            @Parameter(description = "Image URL") @RequestParam String imageUrl) {
        return ResponseEntity.ok(locationService.addLocationImage(id, imageUrl));
    }

    @DeleteMapping("/{id}/images")
    @Operation(summary = "Remove image from location")
    public ResponseEntity<Location> removeLocationImage(
            @Parameter(description = "Location ID") @PathVariable Long id,
            @Parameter(description = "Image URL") @RequestParam String imageUrl) {
        return ResponseEntity.ok(locationService.removeLocationImage(id, imageUrl));
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get locations by category")
    public ResponseEntity<List<Location>> getLocationsByCategory(
            @Parameter(description = "Category name") @PathVariable String category) {
        return ResponseEntity.ok(locationService.getLocationsByCategory(category));
    }

    @GetMapping("/tags")
    @Operation(summary = "Get locations by tags")
    public ResponseEntity<List<Location>> getLocationsByTags(
            @Parameter(description = "List of tags") @RequestParam List<String> tags) {
        return ResponseEntity.ok(locationService.getLocationsByTags(tags));
    }

    @PostMapping("/{id}/visit")
    @Operation(summary = "Increment visit count")
    public ResponseEntity<Location> incrementVisitCount(
            @Parameter(description = "Location ID") @PathVariable Long id) {
        return ResponseEntity.ok(locationService.incrementVisitCount(id));
    }

    @PostMapping("/{id}/rating")
    @Operation(summary = "Update location rating")
    public ResponseEntity<Location> updateRating(
            @Parameter(description = "Location ID") @PathVariable Long id,
            @Parameter(description = "Rating value") @RequestParam double rating) {
        return ResponseEntity.ok(locationService.updateRating(id, rating));
    }
} 