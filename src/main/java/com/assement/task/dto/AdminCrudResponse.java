package com.assement.task.dto;

import com.assement.task.model.entity.Admin;
import com.assement.task.service.AdminCrudService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminCrudResponse {

    @Autowired
    private AdminCrudService userCrudService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void delete(String email) {
        userCrudService.delete(email);
    }

    public Iterable<AdminRequest> get_All_UserProfile() {
        List<Admin> users = userCrudService.findAllUser();

        return users
                .stream()
                .map(user -> modelMapper.map(user, AdminRequest.class))
                .collect(Collectors.toList());
    }

    public AdminRequest get_UserProfile(String email) {
        Admin user = userCrudService.findByEmail(email);

        return modelMapper.map(user,AdminRequest.class);
    }

    public void updateUser(AdminRequest userRequest) {
        userCrudService.updateUser(userRequest);
    }

    public void changePassword(String email, String currentPassword, String newPassword) {

        //converting newPassword to Bcrypt format.
        newPassword = passwordEncoder.encode(newPassword);

        userCrudService.updatePassword(email,currentPassword,newPassword);
    }
}
