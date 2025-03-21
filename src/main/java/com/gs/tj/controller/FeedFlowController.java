package com.gs.tj.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.entity.Diary;
import com.gs.tj.service.FeedFlowService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling feed flow operations.
 * Provides endpoints for paginated content browsing.
 */
@RestController
@RequestMapping("/api/feed")
@RequiredArgsConstructor
@Tag(name = "Feed Flow", description = "APIs for content feed pagination")
public class FeedFlowController {

    private final FeedFlowService feedFlowService;

    @GetMapping
    @Operation(summary = "Get paginated feed content")
    public ResponseEntity<List<Diary>> getFeedPage(
            @Parameter(description = "User ID") 
            @RequestParam Long userId,
            @Parameter(description = "ID of the last item in the current page") 
            @RequestParam(required = false) Long lastId,
            @Parameter(description = "Number of items per page") 
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(feedFlowService.getFeedPage(userId, lastId, pageSize));
    }

    @GetMapping("/location")
    @Operation(summary = "Get feed content by location")
    public ResponseEntity<List<Diary>> getFeedByLocation(
            @Parameter(description = "Location ID") 
            @RequestParam Long locationId,
            @Parameter(description = "ID of the last item in the current page") 
            @RequestParam(required = false) Long lastId,
            @Parameter(description = "Number of items per page") 
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(feedFlowService.getFeedByLocation(locationId, lastId, pageSize));
    }

    @GetMapping("/tag")
    @Operation(summary = "Get feed content by tag")
    public ResponseEntity<List<Diary>> getFeedByTag(
            @Parameter(description = "Tag name") 
            @RequestParam String tag,
            @Parameter(description = "ID of the last item in the current page") 
            @RequestParam(required = false) Long lastId,
            @Parameter(description = "Number of items per page") 
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(feedFlowService.getFeedByTag(tag, lastId, pageSize));
    }

    @GetMapping("/user")
    @Operation(summary = "Get feed content by user")
    public ResponseEntity<List<Diary>> getFeedByUser(
            @Parameter(description = "User ID") 
            @RequestParam Long userId,
            @Parameter(description = "ID of the last item in the current page") 
            @RequestParam(required = false) Long lastId,
            @Parameter(description = "Number of items per page") 
            @RequestParam(defaultValue = "20") int pageSize) {
        return ResponseEntity.ok(feedFlowService.getFeedByUser(userId, lastId, pageSize));
    }
} 