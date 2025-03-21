package com.gs.tj.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gs.tj.entity.Diary;
import com.gs.tj.entity.Location;
import com.gs.tj.service.RecommendationService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of RecommendationService interface.
 * Provides recommendation logic with caching support.
 */
@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    @Cacheable(value = "popularDestinations", key = "#limit")
    public List<Location> getPopularDestinations(int limit) {
        // TODO: Implement actual recommendation logic
        return new ArrayList<>();
    }

    @Override
    @Cacheable(value = "seasonalDestinations", key = "#season + '-' + #limit")
    public List<Location> getSeasonalDestinations(String season, int limit) {
        // TODO: Implement actual recommendation logic
        return new ArrayList<>();
    }

    @Override
    @Cacheable(value = "similarDiaries", key = "#diaryId + '-' + #limit")
    public List<Diary> getSimilarDiaries(Long diaryId, int limit) {
        // TODO: Implement actual recommendation logic
        return new ArrayList<>();
    }

    @Override
    @Cacheable(value = "personalizedFeed", key = "#userId + '-' + #limit")
    public List<Diary> getPersonalizedFeed(Long userId, int limit) {
        // TODO: Implement actual recommendation logic
        return new ArrayList<>();
    }
} 