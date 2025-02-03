package com.etour.tour_service_api.utils;

import com.etour.tour_service_api.dto.TourCategoryDto;
import com.etour.tour_service_api.dto.TourSubcategoryDto;
import com.etour.tour_service_api.entity.*;
import com.etour.tour_service_api.payload.request.ItineraryRequest;
import com.etour.tour_service_api.payload.request.TourPriceRequest;
import com.etour.tour_service_api.payload.request.TourRequest;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 29-01-2025
 */

public class TourUtils {

    public static TourCategoryDto fromTourCategoryEntity(TourCategoryEntity tourCategoryEntity) {
        return TourCategoryDto.builder()
                .id(tourCategoryEntity.getId())
                .referenceId(tourCategoryEntity.getReferenceId())
                .categoryName(tourCategoryEntity.getCategoryName())
                .imageUrl(tourCategoryEntity.getImageUrl())
                .createdAt(tourCategoryEntity.getCreatedAt().toString())
                .updatedAt(tourCategoryEntity.getUpdatedAt().toString())
                .build();
    }

    public static TourSubcategoryDto fromTourSubcategoryEntity(TourSubcategoryEntity tourSubcategoryEntity) {
        return TourSubcategoryDto.builder()
                .id(tourSubcategoryEntity.getId())
                .referenceId(tourSubcategoryEntity.getReferenceId())
                .subCategoryName(tourSubcategoryEntity.getSubCategoryName())
                .imageUrl(tourSubcategoryEntity.getImageUrl())
                .createdAt(tourSubcategoryEntity.getCreatedAt().toString())
                .updatedAt(tourSubcategoryEntity.getUpdatedAt().toString())
                .tourCategoryId(tourSubcategoryEntity.getTourCategoryEntity().getId())
                .build();
    }

    public static TourPriceEntity createTourPriceEntity(TourEntity savedTourEntity, TourPriceRequest tourPriceRequest) {
        return TourPriceEntity.builder()
                .singlePersonPrice(tourPriceRequest.getSinglePersonPrice())
                .extraPersonPrice(tourPriceRequest.getExtraPersonPrice())
                .twinSharingPrice(tourPriceRequest.getTwinSharingPrice())
                .childWithBedPrice(tourPriceRequest.getChildWithBedPrice())
                .childWithoutBedPrice(tourPriceRequest.getChildWithoutBedPrice())
                .tourEntity(savedTourEntity)
                .build();
    }

    public static ItineraryEntity createItineraryEntity(ItineraryRequest itineraryRequest, TourEntity savedTourEntity) {
        return ItineraryEntity.builder()
                .day(itineraryRequest.getDay())
                .itineraryName(itineraryRequest.getItineraryName())
                .description(itineraryRequest.getDescription())
                .tourEntity(savedTourEntity)
                .build();
    }

    public static TourEntity createTourEntity(TourRequest tourRequest) {
        return TourEntity.builder()
                .tourId(UUID.randomUUID().toString())
                .tourName(tourRequest.getTourName())
                .imageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQiekWCHIAty07z8GECv2GJAbdmv1p998meTA&s")
                .description(tourRequest.getDescription())
                .duration(tourRequest.getDuration())
                .startDate(LocalDate.parse(tourRequest.getStartDate()))
                .endDate(LocalDate.parse(tourRequest.getEndDate()))
                .build();
    }

}
