package com.gs.tj.service;

import java.util.List;

import com.gs.tj.entity.Diary;

/**
 * Service interface for feed flow operations.
 * Handles pagination and content loading for the main feed.
 */
public interface FeedFlowService {
    List<Diary> getFeedPage(Long userId, Long lastId, int pageSize);
    List<Diary> getFeedByLocation(Long locationId, Long lastId, int pageSize);
    List<Diary> getFeedByTag(String tag, Long lastId, int pageSize);
    List<Diary> getFeedByUser(Long userId, Long lastId, int pageSize);
} 