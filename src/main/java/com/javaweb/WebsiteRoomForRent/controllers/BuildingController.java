package com.javaweb.WebsiteRoomForRent.controllers;

import com.javaweb.WebsiteRoomForRent.converters.BuildingConverter;
import com.javaweb.WebsiteRoomForRent.converters.ImageConverter;
import com.javaweb.WebsiteRoomForRent.dtos.BuildingDTO;
import com.javaweb.WebsiteRoomForRent.dtos.ImageDTO;
import com.javaweb.WebsiteRoomForRent.entities.BuildingEntity;
import com.javaweb.WebsiteRoomForRent.entities.ImageEntity;
import com.javaweb.WebsiteRoomForRent.services.BuildingService;
import com.javaweb.WebsiteRoomForRent.services.ImageService;
import com.javaweb.WebsiteRoomForRent.utils.ImageUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
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

    @GetMapping
    public ResponseEntity findBuilding(@RequestParam(required = false) Map<String, Object> searchFields) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBuilding(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(buildingService.deleteBuilding(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}