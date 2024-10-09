package com.javaweb.WebsiteRoomForRent.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

public class ImageUtils {
    public static String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID() + "_" + fileName;

        try {
            Path uploadDir = Paths.get("images");
            if(!Files.exists(uploadDir))
                Files.createDirectories(uploadDir);
            Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFilename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Upload Successful";
    }

    // Kiểm tra kích thước file
    public static boolean isImageTooLarge(MultipartFile file) {
        if(file.getSize() > 10 * 1024 * 1024) return true; // Kích thước > 10MB
        return false;
    }
}