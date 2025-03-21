package com.gs.tj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gs.tj.entity.Route;

/**
 * Repository interface for Route entity.
 * Provides methods for route-related database operations.
 */
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    List<Route> findByUserId(Long userId);
    List<Route> findByStartLocationId(Long locationId);
    List<Route> findByEndLocationId(Long locationId);
    List<Route> findByIsPublicTrueOrderByCreatedAtDesc();
} 