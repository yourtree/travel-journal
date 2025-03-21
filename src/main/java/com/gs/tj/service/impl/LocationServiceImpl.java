package com.gs.tj.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gs.tj.entity.Location;
import com.gs.tj.exception.ResourceNotFoundException;
import com.gs.tj.repository.LocationRepository;
import com.gs.tj.service.LocationService;

import lombok.RequiredArgsConstructor;

/**
 * Implementation of LocationService with caching support.
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    @Transactional
    @CacheEvict(value = "locations", allEntries = true)
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    @Cacheable(value = "locations", key = "#id")
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    @Cacheable(value = "locations", key = "'all'")
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public Location updateLocation(Long id, Location locationDetails) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));

        location.setName(locationDetails.getName());
        location.setDescription(locationDetails.getDescription());
        location.setCountry(locationDetails.getCountry());
        location.setCity(locationDetails.getCity());
        location.setCategory(locationDetails.getCategory());
        location.setLatitude(locationDetails.getLatitude());
        location.setLongitude(locationDetails.getLongitude());
        location.setTags(locationDetails.getTags());
        location.setPublic(locationDetails.isPublic());

        return locationRepository.save(location);
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public void deleteLocation(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        locationRepository.delete(location);
    }

    @Override
    @Cacheable(value = "locations", key = "'search:' + #query")
    public List<Location> searchLocations(String query) {
        return locationRepository.findByNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    @Override
    @Cacheable(value = "locations", key = "'country:' + #country")
    public List<Location> getLocationsByCountry(String country) {
        return locationRepository.findByCountry(country);
    }

    @Override
    @Cacheable(value = "locations", key = "'city:' + #city")
    public List<Location> getLocationsByCity(String city) {
        return locationRepository.findByCity(city);
    }

    @Override
    @Cacheable(value = "locations", key = "'popular:' + #limit")
    public List<Location> getPopularLocations(int limit) {
        return locationRepository.findPopularLocations()
                .stream()
                .limit(limit)
                .toList();
    }

    @Override
    @Cacheable(value = "locations", key = "'nearby:' + #latitude + ':' + #longitude + ':' + #radiusInMeters")
    public List<Location> getNearbyLocations(double latitude, double longitude, double radiusInMeters) {
        return locationRepository.findNearbyLocations(latitude, longitude, radiusInMeters);
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public Location addLocationImage(Long id, String imageUrl) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        location.getImageUrls().add(imageUrl);
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public Location removeLocationImage(Long id, String imageUrl) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        location.getImageUrls().remove(imageUrl);
        return locationRepository.save(location);
    }

    @Override
    @Cacheable(value = "locations", key = "'category:' + #category")
    public List<Location> getLocationsByCategory(String category) {
        return locationRepository.findByCategory(category);
    }

    @Override
    @Cacheable(value = "locations", key = "'tags:' + #tags")
    public List<Location> getLocationsByTags(List<String> tags) {
        return tags.stream()
                .flatMap(tag -> locationRepository.findByTagsContaining(tag).stream())
                .distinct()
                .toList();
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public Location incrementVisitCount(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        location.setVisitCount(location.getVisitCount() + 1);
        return locationRepository.save(location);
    }

    @Override
    @Transactional
    @CacheEvict(value = "locations", key = "#id")
    public Location updateRating(Long id, double rating) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id: " + id));
        
        double currentTotal = location.getAverageRating() * location.getRatingCount();
        location.setRatingCount(location.getRatingCount() + 1);
        location.setAverageRating((currentTotal + rating) / location.getRatingCount());
        
        return locationRepository.save(location);
    }
} 