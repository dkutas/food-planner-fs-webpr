package com.fs.webpr.foodplanner_backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class FileUploadService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    public String uploadFile(MultipartFile file) {
        try {
            String originalFilename = Paths.get(Objects.requireNonNull(file.getOriginalFilename())).getFileName().toString();
            String filename = UUID.randomUUID() + "_" + originalFilename;
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            Path filePath = uploadPath.resolve(filename);
            file.transferTo(filePath.toFile());
            return "/images/" + filename;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
