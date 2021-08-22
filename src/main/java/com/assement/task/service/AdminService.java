package com.assement.task.service;

import com.assement.task.model.entity.Admin;

import java.util.HashMap;

public interface AdminService {

    void saveAdmin(Admin user);

    HashMap<String, String> login(String email, String password);
}
