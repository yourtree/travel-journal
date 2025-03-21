package com.gs.tj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.Diary;
import com.gs.tj.entity.Location;
import com.gs.tj.service.AIService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling AI-related operations.
 * Provides endpoints for content generation, sentiment analysis, and travel recommendations.
 */
@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "AI Features", description = "APIs for AI-powered travel features")
public class AIController {

    private final AIService aiService;

    @PostMapping("/diary-content")
    @Operation(summary = "Generate diary content", description = "Generates AI-powered diary content based on location and preferences")
    public ResponseEntity<String> generateDiaryContent(
            @Parameter(description = "Location details") @Valid @RequestBody Location location,
            @Parameter(description = "User preferences and interests") @RequestParam String preferences) {
        return ResponseEntity.ok(aiService.generateDiaryContent(location, preferences));
    }

    @PostMapping("/analyze-sentiment")
    @Operation(summary = "Analyze sentiment", description = "Analyzes the sentiment of diary content")
    public ResponseEntity<String> analyzeSentiment(
            @Parameter(description = "Content to analyze") @RequestParam String content) {
        return ResponseEntity.ok(aiService.analyzeSentiment(content));
    }

    @GetMapping("/recommendations")
    @Operation(summary = "Get travel recommendations", description = "Generates personalized travel recommendations")
    public ResponseEntity<List<Location>> generateTravelRecommendations(
            @Parameter(description = "User ID") @RequestParam Long userId,
            @Parameter(description = "User preferences and interests") @RequestParam String preferences,
            @Parameter(description = "Maximum number of recommendations") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(aiService.generateTravelRecommendations(userId, preferences, limit));
    }

    @PostMapping("/itinerary")
    @Operation(summary = "Generate itinerary", description = "Generates a personalized travel itinerary")
    public ResponseEntity<String> generateItinerary(
            @Parameter(description = "Location details") @Valid @RequestBody Location location,
            @Parameter(description = "Duration in days") @RequestParam int duration,
            @Parameter(description = "User preferences and interests") @RequestParam String preferences) {
        return ResponseEntity.ok(aiService.generateItinerary(location, duration, preferences));
    }

    @GetMapping("/travel-tips")
    @Operation(summary = "Get travel tips", description = "Generates travel tips and advice for a location")
    public ResponseEntity<String> generateTravelTips(
            @Parameter(description = "Location details") @Valid @RequestBody Location location) {
        return ResponseEntity.ok(aiService.generateTravelTips(location));
    }

    @PostMapping("/translate")
    @Operation(summary = "Translate content", description = "Translates diary content to another language")
    public ResponseEntity<String> translateContent(
            @Parameter(description = "Content to translate") @RequestParam String content,
            @Parameter(description = "Target language code (e.g., 'es' for Spanish)") @RequestParam String targetLanguage) {
        return ResponseEntity.ok(aiService.translateContent(content, targetLanguage));
    }

    @PostMapping("/hashtags")
    @Operation(summary = "Generate hashtags", description = "Generates relevant hashtags for diary content")
    public ResponseEntity<List<String>> generateHashtags(
            @Parameter(description = "Content to generate hashtags for") @RequestParam String content) {
        return ResponseEntity.ok(aiService.generateHashtags(content));
    }

    @PostMapping("/highlights")
    @Operation(summary = "Generate story highlights", description = "Generates highlights from a travel diary")
    public ResponseEntity<String> generateStoryHighlights(
            @Parameter(description = "Diary to generate highlights from") @Valid @RequestBody Diary diary) {
        return ResponseEntity.ok(aiService.generateStoryHighlights(diary));
    }
} 