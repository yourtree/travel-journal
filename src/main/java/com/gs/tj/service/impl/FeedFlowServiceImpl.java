package com.gs.tj.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.gs.tj.entity.Diary;
import com.gs.tj.repository.DiaryRepository;
import com.gs.tj.service.FeedFlowService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of FeedFlowService interface.
 * Provides feed flow functionality with caching support.
 */
@Service
@RequiredArgsConstructor
public class FeedFlowServiceImpl implements FeedFlowService {

    private final DiaryRepository diaryRepository;

    @Override
    @Cacheable(value = "feed", key = "'page:' + #userId + ':' + #lastId + ':' + #pageSize")
    public List<Diary> getFeedPage(Long userId, Long lastId, int pageSize) {
        // TODO: Implement pagination logic
        return diaryRepository.findByUserId(userId);
    }

    @Override
    @Cacheable(value = "feed", key = "'location:' + #locationId + ':' + #lastId + ':' + #pageSize")
    public List<Diary> getFeedByLocation(Long locationId, Long lastId, int pageSize) {
        // TODO: Implement pagination logic
        return diaryRepository.findByLocationId(locationId);
    }

    @Override
    @Cacheable(value = "feed", key = "'tag:' + #tag + ':' + #lastId + ':' + #pageSize")
    public List<Diary> getFeedByTag(String tag, Long lastId, int pageSize) {
        // TODO: Implement pagination logic
        return diaryRepository.findByTagsContaining(tag);
    }

    @Override
    @Cacheable(value = "feed", key = "'user:' + #userId + ':' + #lastId + ':' + #pageSize")
    public List<Diary> getFeedByUser(Long userId, Long lastId, int pageSize) {
        // TODO: Implement pagination logic
        return diaryRepository.findByUserId(userId);
    }
} 