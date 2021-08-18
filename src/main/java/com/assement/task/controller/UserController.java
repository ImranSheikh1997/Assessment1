package com.assement.task.controller;

import com.assement.task.dto.UserRequest;
import com.assement.task.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserResponse response;

    @PostMapping("/add_user")
    public ResponseEntity<?> add_user(@RequestBody(required = false) UserRequest request){
        response.addUser(request);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
