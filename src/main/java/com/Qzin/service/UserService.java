package com.Qzin.service;

import com.Qzin.Request.UserUpdateRequestBody;
import com.Qzin.entity.User;

public interface UserService {

    public User addUser(User user);

    public User updateUser(UserUpdateRequestBody userUpdateRequestBody);
}
