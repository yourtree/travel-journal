package com.gs.tj.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.gs.tj.entity.Diary;

/**
 * Service interface for diary management operations.
 * Handles CRUD operations and diary-related functionality.
 */
public interface DiaryService {
    /**
     * Create a new diary entry.
     *
     * @param diary The diary to create
     * @return The created diary
     */
    Diary createDiary(Diary diary);

    /**
     * Get a diary by its ID.
     *
     * @param id The ID of the diary
     * @return Optional containing the diary if found
     */
    Optional<Diary> getDiaryById(Long id);

    /**
     * Get all diaries for a specific user.
     *
     * @param userId The ID of the user
     * @return List of diaries
     */
    List<Diary> getDiariesByUser(Long userId);

    /**
     * Get diaries by location.
     *
     * @param locationId The ID of the location
     * @return List of diaries
     */
    List<Diary> getDiariesByLocation(Long locationId);

    /**
     * Get diaries by date range.
     *
     * @param startDate Start date
     * @param endDate End date
     * @return List of diaries
     */
    List<Diary> getDiariesByDateRange(LocalDate startDate, LocalDate endDate);

    /**
     * Update an existing diary.
     *
     * @param id The ID of the diary to update
     * @param diaryDetails The updated diary details
     * @return The updated diary
     */
    Diary updateDiary(Long id, Diary diaryDetails);

    /**
     * Delete a diary.
     *
     * @param id The ID of the diary to delete
     */
    void deleteDiary(Long id);

    /**
     * Add a comment to a diary.
     *
     * @param diaryId The ID of the diary
     * @param comment The comment to add
     * @return The updated diary
     */
    Diary addComment(Long diaryId, String comment);

    /**
     * Add a tag to a diary.
     *
     * @param diaryId The ID of the diary
     * @param tag The tag to add
     * @return The updated diary
     */
    Diary addTag(Long diaryId, String tag);

    /**
     * Remove a tag from a diary.
     *
     * @param diaryId The ID of the diary
     * @param tag The tag to remove
     * @return The updated diary
     */
    Diary removeTag(Long diaryId, String tag);

    /**
     * Get diaries by tag.
     *
     * @param tag The tag to search for
     * @return List of diaries
     */
    List<Diary> getDiariesByTag(String tag);

    /**
     * Get popular diaries.
     *
     * @param limit Maximum number of diaries to return
     * @return List of popular diaries
     */
    List<Diary> getPopularDiaries(int limit);

    /**
     * Get recommended diaries for a user.
     *
     * @param userId The ID of the user
     * @param limit Maximum number of diaries to return
     * @return List of recommended diaries
     */
    List<Diary> getRecommendedDiaries(Long userId, int limit);
} 