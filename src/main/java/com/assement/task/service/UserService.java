package com.assement.task.service;

import com.assement.task.model.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(User map);

    List<User> findAllUser();
}
