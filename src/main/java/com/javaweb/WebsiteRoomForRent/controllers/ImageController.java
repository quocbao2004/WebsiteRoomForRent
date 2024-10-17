package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.converters.ImageConverter;
import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;
import com.javaweb.WebsiteRoomForRent.services.BuildingService;
import com.javaweb.WebsiteRoomForRent.services.ImageService;
import com.javaweb.WebsiteRoomForRent.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RestController
@RequestMapping("/${api.prefix}/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;
    private final ImageConverter imageConverter;
    private final BuildingService buildingService;

    @PostMapping(value = "/upload-images-vids/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadImages(@RequestBody List<MultipartFile> files, @PathVariable Long id) {
        try {
            BuildingEntity building = buildingService.getBuildingById(id);
            if(building == null) return ResponseEntity.badRequest().body("Building not found");

            files = files == null ? new ArrayList<>() : files;
            List<ImageEntity> buildingImages = building.getImages();

            for (MultipartFile file : files) {
                if(file.getSize() == 0) continue;

                if(ImageUtils.isImageTooLarge(file))
                    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body("File is too large! Maximum size is 10MB");

                String contentType = file.getContentType();
                if(contentType == null || (!contentType.startsWith("image/")
                        && !contentType.startsWith("video/"))) {
                    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                            .body("File must be an image or a video");
                }

                // Lưu file và cập nhật thumbnail trong DTO
                String filename = ImageUtils.storeFile(file);

                //lưu vào đối tượng product trong DB
                ImageEntity image = imageService.createBuildingImage(building.getId(),
                        ImageDTO.builder()
                                .imageUrl(filename)
                                .build()
                );
                buildingImages.add(image);
            }
            return ResponseEntity.ok().body(buildingImages.stream().map(it -> imageConverter.toDTO(it)).toList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = "/get-images-vids/{id}")
    public ResponseEntity getImagesVidsURL(@PathVariable Long id) {
        try {
            List<String> list = imageService.getBuildingImagesNames(id);
            return ResponseEntity.ok().body(list);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping(value = "/display-image-vid")
    public ResponseEntity displayImage(@RequestParam("filename") String fileName) {
        try {
            var fileToDownload = imageService.getBuildingImagesVids(fileName);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentLength(fileToDownload.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(Files.newInputStream(fileToDownload.toPath())));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{fileName}")
    public ResponseEntity deleteBuildingImageVids(@PathVariable String fileName) {
        try {
            return ResponseEntity.ok(imageService.deleteBuildingImage(fileName));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}