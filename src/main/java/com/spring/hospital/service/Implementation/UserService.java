package com.spring.hospital.service.Implementation;

import com.spring.hospital.entity.User;
import com.spring.hospital.repository.UserRepository;
import com.spring.hospital.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> saveUsers(List<User> users) {
        return null;
    }

    @Override
    public User getOneUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
