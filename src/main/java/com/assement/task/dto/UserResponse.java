package com.assement.task.dto;

import com.assement.task.model.entity.Admin;
import com.assement.task.model.entity.User;
import com.assement.task.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserResponse {

    @Autowired
    private UserService service;

    @Autowired
    private ModelMapper mapper;

    public void addUser(UserRequest request) {

        service.saveUser(
                mapper.map(
                        request, User.class
        ));

    }

    public Iterable<UserRequest> findAllUsers() {

            List<User> users = service.findAllUser();

            return users
                    .stream()
                    .map(user -> mapper.map(user, UserRequest.class))
                    .collect(Collectors.toList());

    }
}
