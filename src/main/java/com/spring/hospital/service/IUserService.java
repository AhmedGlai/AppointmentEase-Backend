package com.spring.hospital.service;

import com.spring.hospital.dto.PatientDTO;
import com.spring.hospital.entity.User;

import java.util.List;

public interface IUserService {



    User saveUser (User user);
    User editUser(User user);
    void deleteUser (Long userId);

    List<User> getUsers();
    public List<User> saveUsers(List<User> users);

    User getOneUser(Long userId);
}
