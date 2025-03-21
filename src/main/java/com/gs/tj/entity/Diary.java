package com.gs.tj.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Diary entity representing user travel diaries.
 * Contains diary entries with associated locations and media.
 */
@Entity
@Table(name = "diaries")
@Getter
@Setter
public class Diary extends BaseEntity {

    @NotBlank
    @Size(max = 200)
    @Column(nullable = false)
    private String title;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    private LocalDateTime travelDate;

    @ElementCollection
    @CollectionTable(name = "diary_images", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "image_url")
    private Set<String> imageUrls = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "diary_tags", joinColumns = @JoinColumn(name = "diary_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();

    @Column(nullable = false)
    private Integer likes = 0;

    private boolean isPublic = false;
} 