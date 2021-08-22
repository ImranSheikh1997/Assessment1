package com.assement.task.controller;

import com.assement.task.dto.UserRequest;
import com.assement.task.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/search_user/{email}")
    public ResponseEntity<?> search_user(
            @PathVariable String email
    ){
        return new ResponseEntity<>(response.findUserByEmail(email), HttpStatus.OK);
    }

    @PostMapping("/update-user/{email}")
    public ResponseEntity<?> updateUser(
            @PathVariable String email,
            @RequestBody(required = false) UserRequest request
    ){
        response.updateUser(email,request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-user")
    public ResponseEntity<?> deleteUser(
            @RequestBody String email
    ){
        response.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
