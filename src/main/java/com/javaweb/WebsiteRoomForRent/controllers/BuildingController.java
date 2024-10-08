package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.converters.BuildingConverter;
import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;
import com.javaweb.WebsiteRoomForRent.services.BuildingService;
import com.javaweb.WebsiteRoomForRent.services.ImageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RestController
@RequestMapping("/${api.prefix}/building")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BuildingController {

    private final BuildingService buildingService;
    private final BuildingConverter buildingConverter;
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity findBuilding(@RequestParam Map<String, Object> searchFields) {
        try {
            return ResponseEntity.ok(buildingService.searchBuilding(buildingConverter.toBuildingSearchRequests(searchFields)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity addOrUpdateBuilding(@RequestBody @Valid BuildingDTO buildingDTO, BindingResult bindingResult) {
        try {
            if(bindingResult.hasErrors()) {
                List<String> errorDetails = bindingResult.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                return ResponseEntity.badRequest().body(errorDetails);
            }
            return ResponseEntity.ok(buildingService.createOrUpdateBuilding(buildingDTO));
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = "/upload-images/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImages(@RequestBody List<MultipartFile> files, @PathVariable Long id) {
        try {
            BuildingEntity building = buildingService.getBuildingById(id);

            files = files == null ? new ArrayList<>() : files;
            List<ImageEntity> buildingImages = building.getImages();

            for (MultipartFile file : files) {
                if(file.getSize() == 0) continue;

                // Kiểm tra kích thước file và định dạng
                if(file.getSize() > 10 * 1024 * 1024) { // Kích thước > 10MB
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");
                }
                String contentType = file.getContentType();
                if(contentType == null || !contentType.startsWith("image/")) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image");
                }

                // Lưu file và cập nhật thumbnail trong DTO
                String filename = storeFile(file); // Thay thế hàm này với code của bạn để lưu file
                //lưu vào đối tượng product trong DB
                ImageEntity image = imageService.createBuildingImage(building.getId(),
                        ImageDTO.builder()
                                .imageUrl(filename)
                                .build()
                );
                buildingImages.add(image);
            }
            return ResponseEntity.ok().body(buildingImages);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String storeFile(MultipartFile file) throws IOException{
        if (!isImageFile(file) || file.getOriginalFilename() == null) {
            throw new IOException("Invalid image format");
        }
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID() + "_" + fileName;

        Path uploadDir = Paths.get("imageeee");
        if(!Files.exists(uploadDir))
            Files.createDirectories(uploadDir);
        Path destination = Paths.get(uploadDir.toString(),uniqueFilename);
        Files.copy(file.getInputStream(),destination, StandardCopyOption.REPLACE_EXISTING);
        return uniqueFilename;
    }

    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    @GetMapping(value = "/show-images/{id}")
    public ResponseEntity showImage(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(imageService.getBuildingImages(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBuilding(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(buildingService.deleteBuilding(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}