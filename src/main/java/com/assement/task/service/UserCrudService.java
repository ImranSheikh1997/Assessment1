package com.assement.task.service;

import com.assement.task.dto.UserRequest;
import com.assement.task.model.entity.User;

import java.util.List;

public interface UserCrudService {

    void delete(String email);

    List<User> findAllUser();

    User findByEmail(String email);

    void updateUser(UserRequest userRequest);

    void updatePassword(String email, String currentPassword, String newPassword);
}
