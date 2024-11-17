package com.javaweb.WebsiteRoomForRent.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImageUtils {

    public static String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        String uniqueFilename = UUID.randomUUID() + "_" + fileName;

        try {
            Path uploadDir = Paths.get("images");
            if(!Files.exists(uploadDir)) Files.createDirectories(uploadDir);
            Path destination = Paths.get(uploadDir.toString(), uniqueFilename);
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFilename;
        } catch (Exception e) {
            e.printStackTrace();
            return "Upload Failed";
        }
    }

    // Kiểm tra kích thước file
    public static boolean isImageTooLarge(MultipartFile file) {
        if(file.getSize() > 100 * 1024 * 1024) return true; // Kích thước > 10MB
        return false;
    }

    public static Path findByFileName(Path path, String fileName) throws IOException {
        Path result;
        try (Stream<Path> pathStream = Files.find(path, 2,
                (p, basicFileAttributes) -> p.getFileName().toString().equals(fileName))
        ) {
            result = pathStream.collect(Collectors.toList()).get(0);
        }
        return result;
    }
}