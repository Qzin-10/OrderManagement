package com.Qzin.controller;

import com.Qzin.Request.UserUpdateRequestBody;
import com.Qzin.entity.User;
import com.Qzin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            logger.error("Error occurred while creating user: {}", user, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/updateuser")
    public User updateUser(@RequestBody UserUpdateRequestBody userUpdateRequestBody) {
        try {
            User updatedUser = userService.updateUser(userUpdateRequestBody);
            return updatedUser;
        } catch (Exception e) {
            return null;
        }
    }
}
