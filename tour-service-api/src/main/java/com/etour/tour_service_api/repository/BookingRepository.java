package com.etour.tour_service_api.repository;

import com.etour.tour_service_api.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 05-02-2025
 */

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {
}
