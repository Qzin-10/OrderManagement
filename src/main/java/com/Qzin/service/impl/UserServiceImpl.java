package com.Qzin.service.impl;

import com.Qzin.Request.UserUpdateRequestBody;
import com.Qzin.entity.Item;
import com.Qzin.entity.User;
import com.Qzin.repository.UserRepository;
import com.Qzin.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserUpdateRequestBody userUpdateRequestBody) {
        String userUUID = userUpdateRequestBody.getUserUUID();

        if (userUUID == null) {
            throw new IllegalArgumentException("User UUID cannot be null");
        }

        User user = userRepository.getUserByUUID(userUUID)
                .orElseThrow(() -> new EntityNotFoundException("User not found with UUID: " + userUUID));

        Optional.ofNullable(userUpdateRequestBody.getContactNumber())
                .ifPresent(user::setContactNumber);

        Optional.ofNullable(userUpdateRequestBody.getEmail())
                .ifPresent(user::setEmail);

        Optional.ofNullable(userUpdateRequestBody.getUsername())
                .ifPresent(user::setUsername);

        Optional.ofNullable(userUpdateRequestBody.getIsActive())
                .ifPresent(user::setIsActive);

        userRepository.save(user);

        return user;
    }
}
