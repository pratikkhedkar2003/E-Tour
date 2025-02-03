package com.etour.tour_service_api.resource;

import com.etour.tour_service_api.entity.TourEntity;
import com.etour.tour_service_api.payload.request.TourRequest;
import com.etour.tour_service_api.payload.response.Response;
import com.etour.tour_service_api.service.TourService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.etour.tour_service_api.constant.ApiConstant.TOUR_IMAGE_FILE_STORAGE;
import static com.etour.tour_service_api.utils.RequestUtils.getResponse;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

/**
 * @version 1.0
 * @project tour-service-api
 * @since 30-01-2025
 */

@RestController
@RequestMapping(path = { "/tour" })
@RequiredArgsConstructor
public class TourResource {
    private final TourService tourService;

    @GetMapping("/list")
    public ResponseEntity<Response> getAllTours(HttpServletRequest request) {
        List<TourEntity> tourEntities = tourService.getAllTours();
        return ResponseEntity.ok().body(getResponse(request, of("tours", tourEntities), "Tours retrieved", OK));
    }

    @GetMapping
    public ResponseEntity<Response> getAllToursByTourSubcategoryId(@RequestParam(value = "tourSubcategoryId") Long tourSubcategoryId, HttpServletRequest request) {
        List<TourEntity> tourEntities = tourService.getAllToursByTourSubcategoryId(tourSubcategoryId);
        return ResponseEntity.ok().body(getResponse(request, of("tours", tourEntities), "Tours retrieved", OK));
    }

    @PostMapping("/create")
    public ResponseEntity<Response> createTour(@RequestBody @Valid TourRequest tourRequest, HttpServletRequest request) {
        TourEntity tourEntity = tourService.createTour(tourRequest);
        return ResponseEntity.created(getUri()).body(getResponse(request, of("tour", tourEntity), "Tour created successfully", CREATED));
    }

    @PatchMapping("/upload-image")
    public ResponseEntity<Response> uploadTourImage(
            @RequestParam(value = "tourId") Long tourId,
            @RequestParam(value = "imageFile") MultipartFile imageFile,
            HttpServletRequest request
    ) {
        TourEntity tourEntity = tourService.uploadTourImage(tourId, imageFile);
        return ResponseEntity.ok().body(getResponse(request, of("tour", tourEntity), "Tour image uploaded successfully", OK));
    }

    @GetMapping(path = "/image/{fileName}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable(value = "fileName") String fileName ) throws IOException {
        return Files.readAllBytes(Paths.get(TOUR_IMAGE_FILE_STORAGE + fileName));
    }

    private URI getUri() {
        return URI.create("");
    }

}
