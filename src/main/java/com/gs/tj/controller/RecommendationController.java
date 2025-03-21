package com.gs.tj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.Diary;
import com.gs.tj.entity.Location;
import com.gs.tj.service.RecommendationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling content recommendations.
 * Provides endpoints for getting personalized travel recommendations.
 */
@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(name = "Recommendations", description = "APIs for travel content recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/popular-destinations")
    @Operation(summary = "Get popular travel destinations")
    public ResponseEntity<List<Location>> getPopularDestinations(
            @Parameter(description = "Number of destinations to return") 
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(recommendationService.getPopularDestinations(limit));
    }

    @GetMapping("/seasonal-destinations")
    @Operation(summary = "Get seasonal travel recommendations")
    public ResponseEntity<List<Location>> getSeasonalDestinations(
            @Parameter(description = "Current season (SPRING, SUMMER, FALL, WINTER)") 
            @RequestParam String season,
            @Parameter(description = "Number of destinations to return") 
            @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(recommendationService.getSeasonalDestinations(season, limit));
    }

    @GetMapping("/similar-diaries")
    @Operation(summary = "Get similar travel diaries")
    public ResponseEntity<List<Diary>> getSimilarDiaries(
            @Parameter(description = "ID of the reference diary") 
            @RequestParam Long diaryId,
            @Parameter(description = "Number of diaries to return") 
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(recommendationService.getSimilarDiaries(diaryId, limit));
    }

    @GetMapping("/personalized-feed")
    @Operation(summary = "Get personalized feed recommendations")
    public ResponseEntity<List<Diary>> getPersonalizedFeed(
            @Parameter(description = "User ID") 
            @RequestParam Long userId,
            @Parameter(description = "Number of items to return") 
            @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(recommendationService.getPersonalizedFeed(userId, limit));
    }
} 