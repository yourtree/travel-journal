package com.gs.tj.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.Diary;
import com.gs.tj.service.DiaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling diary-related operations.
 * Provides endpoints for diary management, including CRUD operations and additional features.
 */
@RestController
@RequestMapping("/api/diaries")
@RequiredArgsConstructor
@Tag(name = "Diary Management", description = "APIs for managing travel diaries")
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping
    @Operation(summary = "Create a new diary", description = "Creates a new travel diary entry")
    public ResponseEntity<Diary> createDiary(@Valid @RequestBody Diary diary) {
        return ResponseEntity.ok(diaryService.createDiary(diary));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get diary by ID", description = "Retrieves a specific diary by its ID")
    public ResponseEntity<Diary> getDiaryById(
            @Parameter(description = "ID of the diary to retrieve") @PathVariable Long id) {
        return diaryService.getDiaryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get diaries by user", description = "Retrieves all diaries created by a specific user")
    public ResponseEntity<List<Diary>> getDiariesByUser(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        return ResponseEntity.ok(diaryService.getDiariesByUser(userId));
    }

    @GetMapping("/location/{locationId}")
    @Operation(summary = "Get diaries by location", description = "Retrieves all diaries associated with a specific location")
    public ResponseEntity<List<Diary>> getDiariesByLocation(
            @Parameter(description = "ID of the location") @PathVariable Long locationId) {
        return ResponseEntity.ok(diaryService.getDiariesByLocation(locationId));
    }

    @GetMapping("/date-range")
    @Operation(summary = "Get diaries by date range", description = "Retrieves diaries within a specified date range")
    public ResponseEntity<List<Diary>> getDiariesByDateRange(
            @Parameter(description = "Start date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(diaryService.getDiariesByDateRange(startDate, endDate));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update diary", description = "Updates an existing diary with new details")
    public ResponseEntity<Diary> updateDiary(
            @Parameter(description = "ID of the diary to update") @PathVariable Long id,
            @Valid @RequestBody Diary diaryDetails) {
        return ResponseEntity.ok(diaryService.updateDiary(id, diaryDetails));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete diary", description = "Deletes a specific diary by its ID")
    public ResponseEntity<Void> deleteDiary(
            @Parameter(description = "ID of the diary to delete") @PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{diaryId}/comments")
    @Operation(summary = "Add comment to diary", description = "Adds a comment to a specific diary")
    public ResponseEntity<Diary> addComment(
            @Parameter(description = "ID of the diary") @PathVariable Long diaryId,
            @Parameter(description = "Comment to add") @RequestParam String comment) {
        return ResponseEntity.ok(diaryService.addComment(diaryId, comment));
    }

    @PostMapping("/{diaryId}/tags")
    @Operation(summary = "Add tag to diary", description = "Adds a tag to a specific diary")
    public ResponseEntity<Diary> addTag(
            @Parameter(description = "ID of the diary") @PathVariable Long diaryId,
            @Parameter(description = "Tag to add") @RequestParam String tag) {
        return ResponseEntity.ok(diaryService.addTag(diaryId, tag));
    }

    @DeleteMapping("/{diaryId}/tags/{tag}")
    @Operation(summary = "Remove tag from diary", description = "Removes a tag from a specific diary")
    public ResponseEntity<Diary> removeTag(
            @Parameter(description = "ID of the diary") @PathVariable Long diaryId,
            @Parameter(description = "Tag to remove") @PathVariable String tag) {
        return ResponseEntity.ok(diaryService.removeTag(diaryId, tag));
    }

    @GetMapping("/tag/{tag}")
    @Operation(summary = "Get diaries by tag", description = "Retrieves all diaries with a specific tag")
    public ResponseEntity<List<Diary>> getDiariesByTag(
            @Parameter(description = "Tag to search for") @PathVariable String tag) {
        return ResponseEntity.ok(diaryService.getDiariesByTag(tag));
    }

    @GetMapping("/popular")
    @Operation(summary = "Get popular diaries", description = "Retrieves popular diaries based on likes")
    public ResponseEntity<List<Diary>> getPopularDiaries(
            @Parameter(description = "Maximum number of diaries to return") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(diaryService.getPopularDiaries(limit));
    }

    @GetMapping("/recommended")
    @Operation(summary = "Get recommended diaries", description = "Retrieves personalized diary recommendations for a user")
    public ResponseEntity<List<Diary>> getRecommendedDiaries(
            @Parameter(description = "ID of the user") @RequestParam Long userId,
            @Parameter(description = "Maximum number of diaries to return") @RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(diaryService.getRecommendedDiaries(userId, limit));
    }
} 