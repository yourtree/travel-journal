package com.gs.tj.service;

import java.util.List;

import com.gs.tj.entity.Diary;
import com.gs.tj.entity.Location;

/**
 * Service interface for recommendation-related operations.
 * Provides methods for generating various types of travel recommendations.
 */
public interface RecommendationService {
    List<Location> getPopularDestinations(int limit);
    List<Location> getSeasonalDestinations(String season, int limit);
    List<Diary> getSimilarDiaries(Long diaryId, int limit);
    List<Diary> getPersonalizedFeed(Long userId, int limit);
} 