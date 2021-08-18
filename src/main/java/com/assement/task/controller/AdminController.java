package com.assement.task.controller;

import com.assement.task.dto.AdminCrudResponse;
import com.assement.task.dto.AdminRequest;
import com.assement.task.dto.AdminResponse;
import com.assement.task.dto.LogInRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class AdminController {


    @Autowired
    private AdminResponse response;

    @Autowired
    private AdminCrudResponse crudResponse;

    @PostMapping("/do_register")
    public ResponseEntity<?> register(
            @RequestBody(required = false) AdminRequest user
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

    @GetMapping("/find-all-admin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAllUser(){
        Iterable<AdminRequest> userList = crudResponse.get_All_UserProfile();

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/find-admin/{email}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findUser(
            @PathVariable String email
    ){
        return new ResponseEntity<>(crudResponse.get_UserProfile(email),HttpStatus.OK);
    }

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateUser(
            @RequestBody
                    AdminRequest userRequest
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
