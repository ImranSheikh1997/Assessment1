package com.assement.task.controller;

import com.assement.task.config.security.JwtTokenProvider;
import com.assement.task.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@CrossOrigin("*")
public class AdminController {


    @Autowired
    private AdminResponse response;

    @Autowired
    private AdminCrudResponse crudResponse;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/do_register")
    public ResponseEntity<?> register(
            @RequestBody(required = false) AdminRegistrationRequest user
    ){
        response.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody(required = true) LogInRequest request
    ){
        return new ResponseEntity<>(response.logIn(request), HttpStatus.ACCEPTED);
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

//    @GetMapping("/find-admin/{email}")
    @GetMapping("/findadmin")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> findAdmin(
            HttpServletRequest httpServletRequest
    ){
            String email = jwtTokenProvider.getCurrentUserSession(httpServletRequest.getHeader("Authorization"));
//        String token = jwtTokenProvider.resolveToken(httpServletRequest.getHeader("Authorization"));
//        String email = jwtTokenProvider.getUsername(token);
        return new ResponseEntity<>(crudResponse.get_UserProfile(email),HttpStatus.OK);
    }

    @PutMapping("/update-admin")
    public ResponseEntity<?> updateUser(
            @RequestBody(required = false)
                    AdminRequest userRequest
    )
    {
        crudResponse.updateUser(userRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    //Password Change request
    @PutMapping("/change-password")
    public ResponseEntity<?> updatePassword(
            @RequestParam("currentPassword") String currentPassword,
            @RequestParam("newPassword") String newPassword,
            HttpServletRequest httpServletRequest
    ){
        String email = jwtTokenProvider.getCurrentUserSession(httpServletRequest.getHeader("Authorization"));
        crudResponse.changePassword(email,currentPassword,newPassword);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
