package com.assement.task.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "")
@Data
@ToString
public class User {

    //using int as int can't store null.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

//    @Column(name = "first_name", unique = false, nullable = false)
    private String firstName;

//    @Column(name = "last_name", unique = false, nullable = false)
    private String lastName;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

//    @Column(name = "gender", unique = false, nullable = false)
    private String gender;

//    @Column(name = "address", unique = false, nullable = false)
    private String addressLine;

//    @Column(name = "country", unique = false, nullable = false)
    private String country;

//    @Column(name = "state", unique = false, nullable = false)
    private String state;

//    @Column(name = "zip_code", unique = false, nullable = false)
    private int zipCode;

//    @Column(name = "password", unique = false, nullable = false)
    private String password;

    private String img;
}
