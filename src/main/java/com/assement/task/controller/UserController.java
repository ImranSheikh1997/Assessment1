package com.assement.task.controller;

import com.assement.task.dto.LogInRequest;
import com.assement.task.dto.UserCrudResponse;
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

    @Autowired
    private UserCrudResponse crudResponse;

    @PostMapping("/do_register")
    public ResponseEntity<?> register(
            @RequestBody(required = false) UserRequest user
    ){
        response.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody(required = true) LogInRequest request
    ){
        response.logIn(request);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Crud
    @DeleteMapping(value="/delete-user/{email}")
    public ResponseEntity<?> delete(
            @PathVariable("email") String email){

        crudResponse.delete(email);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }

    @GetMapping("/find-all-user")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllUser(){
        Iterable<UserRequest> userList = crudResponse.get_All_UserProfile();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/find-user/{email}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUser(
            @PathVariable String email
    ){
        return new ResponseEntity<>(crudResponse.get_UserProfile(email),HttpStatus.OK);
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(
            @RequestBody
                    UserRequest userRequest
    )
    {
        crudResponse.updateUser(userRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Password Change request
    @PutMapping("/change-password")
    public ResponseEntity<?> updatePassword(
            @RequestParam("email")String email,
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword
    ){
        crudResponse.changePassword(email,currentPassword,newPassword);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
