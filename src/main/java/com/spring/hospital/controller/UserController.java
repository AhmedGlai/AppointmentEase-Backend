package com.spring.hospital.controller;

import com.spring.hospital.entity.User;
import com.spring.hospital.repository.UserRepository;
import com.spring.hospital.service.Implementation.ImageStorageService;
import com.spring.hospital.service.Implementation.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final ImageStorageService imageStorageService;

    private final UserService userService;

    @PostMapping("/{userId}/profile-image")
    public ResponseEntity<String> uploadProfileImage(
            @PathVariable("userId") Long userId,
            @RequestParam("file") MultipartFile file) {
        User user = userService.getOneUser(userId);
        String fileName = imageStorageService.storeProfileImage(file);
        user.setProfileImage(fileName);
       userService.saveUser(user);
        return ResponseEntity.ok("Profile image uploaded successfully");
    }

    @GetMapping("/{userId}/profile-image")
    public ResponseEntity<Resource> getProfileImage(@PathVariable("userId") Long userId) {
        User user = userService.getOneUser(userId);
        if (user.getProfileImage() == null) {
            throw new IllegalArgumentException("User does not have a profile image");
        }

        Resource imageResource = imageStorageService.loadProfileImage(user.getProfileImage());
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageResource);
    }
}

