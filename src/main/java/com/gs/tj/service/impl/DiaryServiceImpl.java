package com.gs.tj.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.tj.entity.Diary;
import com.gs.tj.exception.ResourceNotFoundException;
import com.gs.tj.repository.DiaryRepository;
import com.gs.tj.service.DiaryService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of DiaryService interface.
 * Provides diary management functionality with caching support.
 */
@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;

    @Override
    @Transactional
    @CacheEvict(value = "diaries", allEntries = true)
    public Diary createDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Override
    @Cacheable(value = "diaries", key = "#id")
    public Optional<Diary> getDiaryById(Long id) {
        return diaryRepository.findById(id);
    }

    @Override
    @Cacheable(value = "diaries", key = "'user:' + #userId")
    public List<Diary> getDiariesByUser(Long userId) {
        return diaryRepository.findByUserId(userId);
    }

    @Override
    @Cacheable(value = "diaries", key = "'location:' + #locationId")
    public List<Diary> getDiariesByLocation(Long locationId) {
        return diaryRepository.findByLocationId(locationId);
    }

    @Override
    @Cacheable(value = "diaries", key = "'dateRange:' + #startDate + ':' + #endDate")
    public List<Diary> getDiariesByDateRange(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findByTravelDateBetween(startDate, endDate);
    }

    @Override
    @Transactional
    @CacheEvict(value = "diaries", key = "#id")
    public Diary updateDiary(Long id, Diary diaryDetails) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found with id: " + id));

        if (diaryDetails.getTitle() != null) {
            diary.setTitle(diaryDetails.getTitle());
        }
        if (diaryDetails.getContent() != null) {
            diary.setContent(diaryDetails.getContent());
        }
        if (diaryDetails.getTravelDate() != null) {
            diary.setTravelDate(diaryDetails.getTravelDate());
        }
        if (diaryDetails.getLocation() != null) {
            diary.setLocation(diaryDetails.getLocation());
        }
        if (diaryDetails.getImageUrls() != null) {
            diary.setImageUrls(diaryDetails.getImageUrls());
        }
        if (diaryDetails.getTags() != null) {
            diary.setTags(diaryDetails.getTags());
        }
        diary.setPublic(diaryDetails.isPublic());

        return diaryRepository.save(diary);
    }

    @Override
    @Transactional
    @CacheEvict(value = "diaries", key = "#id")
    public void deleteDiary(Long id) {
        if (!diaryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Diary not found with id: " + id);
        }
        diaryRepository.deleteById(id);
    }

    @Override
    @Transactional
    @CacheEvict(value = "diaries", key = "#diaryId")
    public Diary addComment(Long diaryId, String comment) {
        // TODO: Implement comment functionality when the Diary entity is updated to include comments
        throw new UnsupportedOperationException("Comment functionality not yet implemented");
    }

    @Override
    @Transactional
    @CacheEvict(value = "diaries", key = "#diaryId")
    public Diary addTag(Long diaryId, String tag) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found with id: " + diaryId));
        diary.getTags().add(tag);
        return diaryRepository.save(diary);
    }

    @Override
    @Transactional
    @CacheEvict(value = "diaries", key = "#diaryId")
    public Diary removeTag(Long diaryId, String tag) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found with id: " + diaryId));
        diary.getTags().remove(tag);
        return diaryRepository.save(diary);
    }

    @Override
    @Cacheable(value = "diaries", key = "'tag:' + #tag")
    public List<Diary> getDiariesByTag(String tag) {
        return diaryRepository.findByTagsContaining(tag);
    }

    @Override
    @Cacheable(value = "diaries", key = "'popular:' + #limit")
    public List<Diary> getPopularDiaries(int limit) {
        return diaryRepository.findPopularDiaries()
                .stream()
                .limit(limit)
                .toList();
    }

    @Override
    @Cacheable(value = "diaries", key = "'recommended:' + #userId + ':' + #limit")
    public List<Diary> getRecommendedDiaries(Long userId, int limit) {
        return diaryRepository.findRecommendedDiaries(userId)
                .stream()
                .limit(limit)
                .toList();
    }
} 