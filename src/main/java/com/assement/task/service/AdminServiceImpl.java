package com.assement.task.service;

import com.assement.task.config.exception.CustomException;
import com.assement.task.config.security.JwtTokenProvider;
import com.assement.task.dao.AdminRepository;
import com.assement.task.model.Role;
import com.assement.task.model.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Optional;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void saveAdmin(Admin user) {
        Role roles = Role.valueOf("ROLE_ADMIN");
        user.setRoles(roles);
        adminRepository.save(user);
    }

    @Override
    public HashMap<String, String> login(String email, String password) {

        HashMap<String,String> map = new HashMap<String,String>();
        try{
            Optional<Admin> user = adminRepository.findByEmail(email);

            if (!user.isPresent()) {
                throw new CustomException("Invalid username supplied", HttpStatus.UNAUTHORIZED);
            } else {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

                String token = jwtTokenProvider.createToken(email, Collections.singletonList(user.get().getRoles()));

                map.put("jwt",token);
                map.put("role",user.get().getRoles().toString());

                log.info("Log In Credentials -> ",map);

                return map;
            }

        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNAUTHORIZED);
        }
    }
}
