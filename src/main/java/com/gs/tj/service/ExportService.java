package com.gs.tj.service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;

/**
 * Service interface for data export operations.
 * Handles exporting diaries, routes, and other content in various formats.
 */
public interface ExportService {
    /**
     * Export a diary to PDF format.
     *
     * @param diaryId ID of the diary to export
     * @return ByteArrayOutputStream containing the PDF data
     */
    ByteArrayOutputStream exportDiaryToPdf(Long diaryId);

    /**
     * Export a diary to Markdown format.
     *
     * @param diaryId ID of the diary to export
     * @return ByteArrayOutputStream containing the Markdown data
     */
    ByteArrayOutputStream exportDiaryToMarkdown(Long diaryId);

    /**
     * Export a route to GPX format.
     *
     * @param routeId ID of the route to export
     * @return ByteArrayOutputStream containing the GPX data
     */
    ByteArrayOutputStream exportRouteToGpx(Long routeId);

    /**
     * Export a route to KML format.
     *
     * @param routeId ID of the route to export
     * @return ByteArrayOutputStream containing the KML data
     */
    ByteArrayOutputStream exportRouteToKml(Long routeId);

    /**
     * Export user's travel statistics to Excel format.
     *
     * @param userId ID of the user
     * @param startDate Start date for the statistics
     * @param endDate End date for the statistics
     * @return ByteArrayOutputStream containing the Excel data
     */
    ByteArrayOutputStream exportUserStatsToExcel(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * Export user's travel memories to a ZIP file containing images and metadata.
     *
     * @param userId ID of the user
     * @param startDate Start date for the memories
     * @param endDate End date for the memories
     * @return ByteArrayOutputStream containing the ZIP data
     */
    ByteArrayOutputStream exportUserMemoriesToZip(Long userId, LocalDate startDate, LocalDate endDate);

    /**
     * Export user's travel timeline to JSON format.
     *
     * @param userId ID of the user
     * @return ByteArrayOutputStream containing the JSON data
     */
    ByteArrayOutputStream exportUserTimelineToJson(Long userId);
} 