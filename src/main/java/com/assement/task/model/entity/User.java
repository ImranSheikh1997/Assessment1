package com.assement.task.model.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    //    @Column(name = "first_name", unique = false, nullable = false)
    private String name;

    //    @Column(name = "last_name", unique = false, nullable = false)
    private String email;

    //    @Column(name = "address", unique = false, nullable = false)
    private String mobileNumber;

    //    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
