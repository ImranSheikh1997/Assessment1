package com.assement.task.dto;

import com.assement.task.model.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;

    private String name;

    private String email;

    private String mobileNumber;

    private String address;

    private Long admin_id;
}
