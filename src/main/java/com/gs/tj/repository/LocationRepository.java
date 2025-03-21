package com.gs.tj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gs.tj.entity.Location;

/**
 * Repository interface for Location entity.
 * Provides methods for location-related database operations.
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String name, String description);
    List<Location> findByCountry(String country);
    List<Location> findByCity(String city);
    List<Location> findByCategory(String category);
    List<Location> findByTagsContaining(String tag);

    @Query("SELECT l FROM Location l WHERE l.isPublic = true ORDER BY l.visitCount DESC")
    List<Location> findPopularLocations();

    @Query(value = "SELECT * FROM locations WHERE " +
            "ST_Distance_Sphere(point(longitude, latitude), point(:longitude, :latitude)) <= :radiusInMeters " +
            "ORDER BY ST_Distance_Sphere(point(longitude, latitude), point(:longitude, :latitude))",
            nativeQuery = true)
    List<Location> findNearbyLocations(
            @Param("latitude") double latitude,
            @Param("longitude") double longitude,
            @Param("radiusInMeters") double radiusInMeters);
} 