package com.assement.task.dto;

import com.assement.task.model.entity.User;
import com.assement.task.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserResponse {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserRequest user) {

        //Encoding Password to Bcrypt format
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        service.saveUser(
            mapper.map(
                    user, User.class
            )
        );
    }

    public void logIn(LogInRequest request) {
        service.login(request.getEmail(),request.getPassword());
    }
}
