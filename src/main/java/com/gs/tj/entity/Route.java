package com.gs.tj.entity;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Route entity representing travel routes between locations.
 * Contains route information, duration, and associated stops.
 */
@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route extends BaseEntity {

    @NotBlank
    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "start_location_id", nullable = false)
    private Location startLocation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_location_id", nullable = false)
    private Location endLocation;

    @Column(nullable = false)
    private Duration estimatedDuration;

    @ElementCollection
    @CollectionTable(name = "route_stops", joinColumns = @JoinColumn(name = "route_id"))
    @OrderColumn(name = "stop_order")
    private Set<Long> stopLocationIds = new HashSet<>();

    private String description;

    @ElementCollection
    @CollectionTable(name = "route_images", joinColumns = @JoinColumn(name = "route_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    private boolean isPublic = false;
} 