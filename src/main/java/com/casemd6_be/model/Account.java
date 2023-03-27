package com.casemd6_be.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;

    @Column(unique = true)
    private String phone;

    private String address;
    private String avatar;
    private Boolean status;
    private String description;
    private String banner;

    @ManyToOne
    private Role role;
}
