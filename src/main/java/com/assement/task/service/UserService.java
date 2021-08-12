package com.assement.task.service;

import com.assement.task.model.entity.User;

public interface UserService {

    void saveUser(User user);

    void login(String email, String password);
}
