package com.etour.tour_service_api.repository;

import com.etour.tour_service_api.entity.TourEntity;
import com.etour.tour_service_api.entity.TourSubcategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 29-01-2025
 */

@Repository
public interface TourRepository extends JpaRepository<TourEntity, Long> {
    List<TourEntity> findAllByTourSubcategoryEntity(TourSubcategoryEntity tourSubcategoryEntity);
}
