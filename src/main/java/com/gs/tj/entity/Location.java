package com.gs.tj.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Location entity representing travel destinations.
 * Contains location information, coordinates, and associated media.
 */
@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location extends BaseEntity {

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String name;

    @Size(max = 1000)
    private String description;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String country;

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false)
    private String city;

    @Size(max = 100)
    private String category;

    @NotNull
    @Column(nullable = false)
    private Double latitude;

    @NotNull
    @Column(nullable = false)
    private Double longitude;

    @ElementCollection
    @CollectionTable(name = "location_images", joinColumns = @JoinColumn(name = "location_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "location_tags", joinColumns = @JoinColumn(name = "location_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @OneToMany(mappedBy = "location", cascade = jakarta.persistence.CascadeType.ALL)
    private Set<Diary> diaries = new HashSet<>();

    @Column(nullable = false)
    private Integer visitCount = 0;

    @Column(nullable = false)
    private Double averageRating = 0.0;

    @Column(nullable = false)
    private Integer ratingCount = 0;

    private boolean isPublic = true;
} 