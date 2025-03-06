package com.javaweb.WebsiteRoomForRent.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"customers\"")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "demand")
    private String demand;

    @Column (name = "status")
    private String status;

    @Column (name="is_active")
    private int isActive;

    @ManyToOne
    @JoinColumn(name ="userid")
    private UserEntity userid;
}