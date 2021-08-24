package com.assement.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRegistrationRequest {
        private String firstName;
        private String lastName;
        private String email;
        private String gender;
        private String addressLine;
        private String country;
        private String state;
        private int zipCode;
        private String password;
    }

