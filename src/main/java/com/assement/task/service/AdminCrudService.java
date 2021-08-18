package com.assement.task.service;

import com.assement.task.dto.AdminRequest;
import com.assement.task.model.entity.Admin;

import java.util.List;

public interface AdminCrudService {

    void delete(String email);

    List<Admin> findAllUser();

    Admin findByEmail(String email);

    void updateUser(AdminRequest userRequest);

    void updatePassword(String email, String currentPassword, String newPassword);
}
