package com.javaweb.WebsiteRoomForRent.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@Table(name = "forgot_password")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String otp;

    @Column(nullable = false)
    private Date expiration;

    @OneToOne
    private UserEntity user;
}