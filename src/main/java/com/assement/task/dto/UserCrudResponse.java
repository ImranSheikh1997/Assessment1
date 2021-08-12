package com.assement.task.dto;

import com.assement.task.model.entity.User;
import com.assement.task.service.UserCrudService;
import com.assement.task.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCrudResponse {

    @Autowired
    private UserCrudService userCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void delete(String email) {
        userCrudService.delete(email);
    }

    public Iterable<UserRequest> get_All_UserProfile() {
        List<User> users = userCrudService.findAllUser();

        return users
                .stream()
                .map(user -> modelMapper.map(user, UserRequest.class))
                .collect(Collectors.toList());
    }

    public UserRequest get_UserProfile(String email) {
        User user = userCrudService.findByEmail(email);
        return modelMapper.map(user,UserRequest.class);
    }

    public void updateUser(UserRequest userRequest) {
        userCrudService.updateUser(userRequest);
    }

    public void changePassword(String email, String currentPassword, String newPassword) {

        newPassword = passwordEncoder.encode(newPassword);
        //converting newPassword to Bcrypt format.
        userCrudService.updatePassword(email,currentPassword,newPassword);
    }
}
