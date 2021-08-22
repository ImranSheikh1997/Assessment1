package com.assement.task.service;

import com.assement.task.dao.UserRepository;
import com.assement.task.dto.UserRequest;
import com.assement.task.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public void saveUser(User user) {
        repository.save(user);
    }

    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }

    @Override
    public User findUserByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public void updateUser(String email, UserRequest request) {
        repository.updateUser(
                email,
                request.getAddress(),
                request.getMobileNumber(),
                request.getName()
        );
    }

    @Override
    public void deleteUser(String email) {
        repository.deleteByEmail(email);
    }
}
