package com.etour.tour_service_api.service;

import com.etour.tour_service_api.entity.TourEntity;
import com.etour.tour_service_api.payload.request.TourRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 29-01-2025
 */

public interface TourService {
    TourEntity createTour(TourRequest tourRequest);
    TourEntity uploadTourImage(Long tourId, MultipartFile imageFile);
    List<TourEntity> getAllTours();
    List<TourEntity> getAllToursByTourSubcategoryId(Long tourSubcategoryId);
}
