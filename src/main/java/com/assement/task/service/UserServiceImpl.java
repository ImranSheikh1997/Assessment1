package com.assement.task.service;

import com.assement.task.config.exception.CustomException;
import com.assement.task.dao.UserRepository;
import com.assement.task.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void saveUser(User user) {

        userRepository.save(
                user
        );
    }

    @Override
    public void login(String email, String password) {
        try {
            Optional<User> user = userRepository.findByEmail(email);
            if (!user.isPresent()) {
                throw new CustomException("Invalid username supplied", HttpStatus.UNAUTHORIZED);
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            }

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }
}
