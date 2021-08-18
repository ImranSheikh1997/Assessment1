package com.assement.task.service;

import com.assement.task.dao.UserRepository;
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
}
