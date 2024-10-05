package com.javaweb.WebsiteRoomForRent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "fullname", nullable = false)
    private String fullname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name="note")
    private String note;

    @Column(name ="address")
    private String address;

    @Column(name="role")
    private String role;

    @OneToMany(mappedBy = "userid", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<BuildingEntity> buildings = new ArrayList<>();

    @OneToMany(mappedBy = "userid", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CustomerEntity> customers = new ArrayList<>();
}