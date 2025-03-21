package com.gs.tj.service;

import java.util.List;
import java.util.Optional;

import com.gs.tj.entity.Location;

/**
 * Service interface for location management operations.
 * Handles CRUD operations and location-based queries.
 */
public interface LocationService {
    /**
     * Create a new location.
     *
     * @param location The location to create
     * @return The created location
     */
    Location createLocation(Location location);

    /**
     * Get a location by its ID.
     *
     * @param id The ID of the location
     * @return Optional containing the location if found
     */
    Optional<Location> getLocationById(Long id);

    /**
     * Get all locations.
     *
     * @return List of all locations
     */
    List<Location> getAllLocations();

    /**
     * Update an existing location.
     *
     * @param id The ID of the location to update
     * @param locationDetails The updated location details
     * @return The updated location
     */
    Location updateLocation(Long id, Location locationDetails);

    /**
     * Delete a location.
     *
     * @param id The ID of the location to delete
     */
    void deleteLocation(Long id);

    /**
     * Search locations by name or description.
     *
     * @param query The search query
     * @return List of matching locations
     */
    List<Location> searchLocations(String query);

    /**
     * Get locations by country.
     *
     * @param country The country name
     * @return List of locations in the country
     */
    List<Location> getLocationsByCountry(String country);

    /**
     * Get locations by city.
     *
     * @param city The city name
     * @return List of locations in the city
     */
    List<Location> getLocationsByCity(String city);

    /**
     * Get popular locations.
     *
     * @param limit Maximum number of locations to return
     * @return List of popular locations
     */
    List<Location> getPopularLocations(int limit);

    /**
     * Get nearby locations.
     *
     * @param latitude The latitude coordinate
     * @param longitude The longitude coordinate
     * @param radiusInMeters The radius in meters
     * @return List of nearby locations
     */
    List<Location> getNearbyLocations(double latitude, double longitude, double radiusInMeters);

    /**
     * Add an image to a location.
     *
     * @param id The ID of the location
     * @param imageUrl The URL of the image to add
     * @return The updated location
     */
    Location addLocationImage(Long id, String imageUrl);

    /**
     * Remove an image from a location.
     *
     * @param id The ID of the location
     * @param imageUrl The URL of the image to remove
     * @return The updated location
     */
    Location removeLocationImage(Long id, String imageUrl);

    /**
     * Get locations by category.
     *
     * @param category The category name
     * @return List of locations in the category
     */
    List<Location> getLocationsByCategory(String category);

    /**
     * Get locations by tags.
     *
     * @param tags List of tags to search for
     * @return List of locations matching any of the tags
     */
    List<Location> getLocationsByTags(List<String> tags);

    /**
     * Increment the visit count for a location.
     *
     * @param id The ID of the location
     * @return The updated location
     */
    Location incrementVisitCount(Long id);

    /**
     * Update the rating for a location.
     *
     * @param id The ID of the location
     * @param rating The new rating value
     * @return The updated location
     */
    Location updateRating(Long id, double rating);
} 