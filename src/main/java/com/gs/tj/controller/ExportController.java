package com.gs.tj.controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gs.tj.service.ExportService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

/**
 * Controller for handling data export operations.
 * Provides endpoints for exporting diaries, routes, and user data in various formats.
 */
@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
@Tag(name = "Data Export", description = "APIs for exporting travel data in various formats")
public class ExportController {

    private final ExportService exportService;

    // Custom MediaType constants
    private static final MediaType APPLICATION_GPX_XML = MediaType.parseMediaType("application/gpx+xml");
    private static final MediaType APPLICATION_VND_GOOGLE_EARTH_KML_XML = MediaType.parseMediaType("application/vnd.google-earth.kml+xml");
    private static final MediaType APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_SPREADSHEETML_SHEET = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    private static final MediaType APPLICATION_ZIP = MediaType.parseMediaType("application/zip");

    @GetMapping("/diary/{diaryId}/pdf")
    @Operation(summary = "Export diary to PDF", description = "Exports a diary to PDF format")
    public ResponseEntity<byte[]> exportDiaryToPdf(
            @Parameter(description = "ID of the diary to export") @PathVariable Long diaryId) {
        ByteArrayOutputStream outputStream = exportService.exportDiaryToPdf(diaryId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=diary_" + diaryId + ".pdf")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/diary/{diaryId}/markdown")
    @Operation(summary = "Export diary to Markdown", description = "Exports a diary to Markdown format")
    public ResponseEntity<byte[]> exportDiaryToMarkdown(
            @Parameter(description = "ID of the diary to export") @PathVariable Long diaryId) {
        ByteArrayOutputStream outputStream = exportService.exportDiaryToMarkdown(diaryId);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_MARKDOWN)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=diary_" + diaryId + ".md")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/route/{routeId}/gpx")
    @Operation(summary = "Export route to GPX", description = "Exports a route to GPX format")
    public ResponseEntity<byte[]> exportRouteToGpx(
            @Parameter(description = "ID of the route to export") @PathVariable Long routeId) {
        ByteArrayOutputStream outputStream = exportService.exportRouteToGpx(routeId);
        return ResponseEntity.ok()
                .contentType(APPLICATION_GPX_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=route_" + routeId + ".gpx")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/route/{routeId}/kml")
    @Operation(summary = "Export route to KML", description = "Exports a route to KML format")
    public ResponseEntity<byte[]> exportRouteToKml(
            @Parameter(description = "ID of the route to export") @PathVariable Long routeId) {
        ByteArrayOutputStream outputStream = exportService.exportRouteToKml(routeId);
        return ResponseEntity.ok()
                .contentType(APPLICATION_VND_GOOGLE_EARTH_KML_XML)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=route_" + routeId + ".kml")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/user/{userId}/stats/excel")
    @Operation(summary = "Export user stats to Excel", description = "Exports user's travel statistics to Excel format")
    public ResponseEntity<byte[]> exportUserStatsToExcel(
            @Parameter(description = "ID of the user") @PathVariable Long userId,
            @Parameter(description = "Start date for statistics") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date for statistics") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ByteArrayOutputStream outputStream = exportService.exportUserStatsToExcel(userId, startDate, endDate);
        return ResponseEntity.ok()
                .contentType(APPLICATION_VND_OPENXMLFORMATS_OFFICEDOCUMENT_SPREADSHEETML_SHEET)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_" + userId + "_stats.xlsx")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/user/{userId}/memories/zip")
    @Operation(summary = "Export user memories to ZIP", description = "Exports user's travel memories to a ZIP file")
    public ResponseEntity<byte[]> exportUserMemoriesToZip(
            @Parameter(description = "ID of the user") @PathVariable Long userId,
            @Parameter(description = "Start date for memories") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "End date for memories") @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ByteArrayOutputStream outputStream = exportService.exportUserMemoriesToZip(userId, startDate, endDate);
        return ResponseEntity.ok()
                .contentType(APPLICATION_ZIP)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_" + userId + "_memories.zip")
                .body(outputStream.toByteArray());
    }

    @GetMapping("/user/{userId}/timeline/json")
    @Operation(summary = "Export user timeline to JSON", description = "Exports user's travel timeline to JSON format")
    public ResponseEntity<byte[]> exportUserTimelineToJson(
            @Parameter(description = "ID of the user") @PathVariable Long userId) {
        ByteArrayOutputStream outputStream = exportService.exportUserTimelineToJson(userId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=user_" + userId + "_timeline.json")
                .body(outputStream.toByteArray());
    }
} 