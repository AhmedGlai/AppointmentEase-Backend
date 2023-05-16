package com.spring.hospital.service.Implementation;

import com.spring.hospital.entity.User;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageStorageService {
    private static final String UPLOAD_DIR = "C:/Users/Firas/OneDrive/Bureau/Projects/ProjetFederateur/Hospital-Backend/images";
    @Autowired
    private UserService userService;

    public String storeProfileImage(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new IllegalArgumentException("File is empty");
            }

            String fileName = generateUniqueFileName(file.getOriginalFilename());
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store profile image", e);
        }
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        String fileName = UUID.randomUUID().toString() + "." + extension;
        return fileName;
    }

    public Resource loadProfileImage(String fileName) {
        try {
            Path filePath = Paths.get(UPLOAD_DIR, fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Profile image not found");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new RuntimeException("Failed to load profile image", e);
        }
    }
    public Resource loadProfileImageByUserId(Long userId) {
        try {
            User user = userService.getOneUser(userId);
            String profileImageFilename = user.getProfileImage();
            if (profileImageFilename == null || profileImageFilename.isEmpty()) {
                throw new FileNotFoundException("Profile image not found");
            }

            Path filePath = Paths.get(UPLOAD_DIR, profileImageFilename);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException("Profile image not found");
            }
        } catch (MalformedURLException | FileNotFoundException e) {
            throw new RuntimeException("Failed to load profile image", e);
        }
    }

}

