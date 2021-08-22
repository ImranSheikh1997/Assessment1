package com.assement.task.dto;

import com.assement.task.model.entity.Admin;
import com.assement.task.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AdminResponse {

    @Autowired
    private AdminService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(AdminRequest user) {

        //Encoding Password to Bcrypt format
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        service.saveAdmin(
            mapper.map(
                    user, Admin.class
            )
        );
    }

    public HashMap<String, String> logIn(LogInRequest request) {
        return service.login(request.getEmail(),request.getPassword());
    }
}
