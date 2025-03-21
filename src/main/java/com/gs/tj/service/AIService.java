package com.gs.tj.service;

import java.util.List;

import com.gs.tj.entity.Diary;
import com.gs.tj.entity.Location;

/**
 * Service interface for AI-related operations.
 * Handles content generation, sentiment analysis, and travel recommendations.
 */
public interface AIService {
    /**
     * Generate travel diary content based on location and preferences.
     *
     * @param location The travel location
     * @param preferences User preferences and interests
     * @return Generated diary content
     */
    String generateDiaryContent(Location location, String preferences);

    /**
     * Analyze sentiment of diary content.
     *
     * @param content The diary content to analyze
     * @return Sentiment analysis result (POSITIVE, NEGATIVE, or NEUTRAL)
     */
    String analyzeSentiment(String content);

    /**
     * Generate travel recommendations based on user preferences and history.
     *
     * @param userId The ID of the user
     * @param preferences User preferences and interests
     * @param limit Maximum number of recommendations to return
     * @return List of recommended locations
     */
    List<Location> generateTravelRecommendations(Long userId, String preferences, int limit);

    /**
     * Generate personalized travel itinerary.
     *
     * @param location The travel location
     * @param duration Duration of the trip in days
     * @param preferences User preferences and interests
     * @return Generated itinerary
     */
    String generateItinerary(Location location, int duration, String preferences);

    /**
     * Generate travel tips and advice for a location.
     *
     * @param location The travel location
     * @return Generated travel tips
     */
    String generateTravelTips(Location location);

    /**
     * Translate diary content to another language.
     *
     * @param content The content to translate
     * @param targetLanguage Target language code (e.g., "es" for Spanish)
     * @return Translated content
     */
    String translateContent(String content, String targetLanguage);

    /**
     * Generate hashtags for diary content.
     *
     * @param content The diary content
     * @return List of relevant hashtags
     */
    List<String> generateHashtags(String content);

    /**
     * Generate travel story highlights.
     *
     * @param diary The diary to generate highlights from
     * @return Generated story highlights
     */
    String generateStoryHighlights(Diary diary);
} 