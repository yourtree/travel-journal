package com.gs.tj.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Favorite entity representing user's favorite items.
 * Can be used to track favorite locations, diaries, or routes.
 */
@Entity
@Table(name = "favorites")
@Getter
@Setter
public class Favorite extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FavoriteType type;

    @Column(name = "favorite_id", nullable = false)
    private Long favoriteId;

    public enum FavoriteType {
        LOCATION,
        DIARY,
        ROUTE
    }
} 