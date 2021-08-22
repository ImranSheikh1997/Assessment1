package com.assement.task.service;

import com.assement.task.dto.UserRequest;
import com.assement.task.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User map);

    List<User> findAllUser();

    User findUserByEmail(String email);

    void updateUser(String email, UserRequest request);

    void deleteUser(String email);
}
