package com.assement.task.service;

import com.assement.task.config.exception.CustomException;
import com.assement.task.dao.UserRepository;
import com.assement.task.dto.UserRequest;
import com.assement.task.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCrudServiceImpl implements UserCrudService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email).get();
    }

    @Override
    public void updateUser(UserRequest userRequest) {
        repository.updateUserByEmail(
                userRequest.getEmail(),
                userRequest.getAddressLine(),
                userRequest.getCountry(),
                userRequest.getFirstName(),
                userRequest.getGender(),
                userRequest.getLastName(),
                userRequest.getState(),
                userRequest.getZipCode()
        );
    }

    @Override
    public void updatePassword(String email, String currentPassword, String newPassword) {

        /*To check User is available and current Password is valid password*/
        try{
            Optional<User> user = repository.findByEmail(email);
            if (!user.isPresent()) {
                throw new CustomException("Invalid username supplied", HttpStatus.UNAUTHORIZED);
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, currentPassword));
            }

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }


        /*Now Update password as currentPassword is valid password*/
        repository.updatePasswordByEmail(email,newPassword);
    }
}
