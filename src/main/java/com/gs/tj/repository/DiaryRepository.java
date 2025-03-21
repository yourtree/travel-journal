package com.gs.tj.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.gs.tj.entity.Diary;

/**
 * Repository interface for Diary entity.
 * Provides methods for diary-related database operations.
 */
@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {
    List<Diary> findByUserId(Long userId);
    List<Diary> findByLocationId(Long locationId);
    List<Diary> findByTravelDateBetween(LocalDate startDate, LocalDate endDate);
    List<Diary> findByTagsContaining(String tag);
    
    @Query("SELECT d FROM Diary d WHERE d.isPublic = true ORDER BY d.likes DESC")
    List<Diary> findPopularDiaries();
    
    @Query("SELECT d FROM Diary d WHERE d.isPublic = true AND d.user.id != :userId ORDER BY d.likes DESC")
    List<Diary> findRecommendedDiaries(@Param("userId") Long userId);
} 