package com.etour.tour_service_api.service.implementation;

import com.etour.tour_service_api.entity.TourEntity;
import com.etour.tour_service_api.entity.TourSubcategoryEntity;
import com.etour.tour_service_api.exception.ApiException;
import com.etour.tour_service_api.payload.request.ItineraryRequest;
import com.etour.tour_service_api.payload.request.TourPriceRequest;
import com.etour.tour_service_api.payload.request.TourRequest;
import com.etour.tour_service_api.repository.ItineraryRepository;
import com.etour.tour_service_api.repository.TourPriceRepository;
import com.etour.tour_service_api.repository.TourRepository;
import com.etour.tour_service_api.repository.TourSubCategoryRepository;
import com.etour.tour_service_api.service.TourService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.BiFunction;

import static com.etour.tour_service_api.constant.ApiConstant.TOUR_IMAGE_FILE_STORAGE;
import static com.etour.tour_service_api.utils.TourUtils.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 30-01-2025
 */

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackOn = Exception.class)
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final TourPriceRepository tourPriceRepository;
    private final ItineraryRepository itineraryRepository;
    private final TourSubCategoryRepository tourSubCategoryRepository;

    @Override
    public TourEntity createTour(TourRequest tourRequest) {
        TourSubcategoryEntity tourSubcategoryEntity = getTourSubcategoryEntityById(tourRequest.getTourSubcategoryId());
        TourEntity tourEntity = createTourEntity(tourRequest);
        tourEntity.setTourSubcategoryEntity(tourSubcategoryEntity);
        TourEntity savedTourEntity = tourRepository.save(tourEntity);
        saveTourItineraries(savedTourEntity, tourRequest.getItineraryRequests());
        saveTourPriceEntity(savedTourEntity, tourRequest.getTourPriceRequest());
        return savedTourEntity;
    }

    @Override
    public TourEntity uploadTourImage(Long tourId, MultipartFile imageFile) {
        TourEntity tourEntity = getTourEntityById(tourId);
        tourEntity.setImageUrl(photoFunction.apply(tourEntity.getTourId(), imageFile));
        return tourRepository.save(tourEntity);
    }

    @Override
    public List<TourEntity> getAllTours() {
        return tourRepository.findAll();
    }

    @Override
    public List<TourEntity> getAllToursByTourSubcategoryId(Long tourSubcategoryId) {
        return tourRepository.findAllByTourSubcategoryEntity(getTourSubcategoryEntityById(tourSubcategoryId));
    }

    private final BiFunction<String, MultipartFile, String> photoFunction = (tourId, file) -> {
        String fileName = tourId + ".png";
        try {
            Path fileStorageLocation = Paths.get(TOUR_IMAGE_FILE_STORAGE).toAbsolutePath().normalize();
            if (!Files.exists(fileStorageLocation)) {
                Files.createDirectories(fileStorageLocation);
            }
            Files.copy(file.getInputStream(), fileStorageLocation.resolve(fileName), REPLACE_EXISTING);
            // /tour-category/image/964d4475-ebd7-1a06-9842-37143e2c2ceb.png?timestamp=1738144361295
            return "http://localhost:8085/api/v1/tour-service/tour/image/" + fileName;
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("Unable to save image");
        }
    };

    private void saveTourPriceEntity(TourEntity savedTourEntity, TourPriceRequest tourPriceRequest) {
        tourPriceRepository.save(createTourPriceEntity(savedTourEntity, tourPriceRequest));
    }

    private void saveTourItineraries(TourEntity savedTourEntity, List<ItineraryRequest> itineraryRequests) {
        for (ItineraryRequest itineraryRequest : itineraryRequests) {
            itineraryRepository.save(createItineraryEntity(itineraryRequest, savedTourEntity));
        }
    }

    private TourEntity getTourEntityById(Long tourId) {
        return tourRepository.findById(tourId).orElseThrow(() -> new ApiException("Tour not found"));
    }

    private TourSubcategoryEntity getTourSubcategoryEntityById(Long tourSubcategoryId) {
        return tourSubCategoryRepository.findById(tourSubcategoryId).orElseThrow(() -> new ApiException("Tour SubCategory not found"));
    }
}
