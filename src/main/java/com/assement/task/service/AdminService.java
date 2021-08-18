package com.assement.task.service;

import com.assement.task.model.entity.Admin;

public interface AdminService {

    void saveAdmin(Admin user);

    void login(String email, String password);
}
