package com.javaweb.WebsiteRoomForRent.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "\"building\"")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name ="ward")
    private String ward;

    @Column(name ="type")
    private String type;

    @Column(name ="district")
    private String district;

    @Column(name ="street")
    private String street;

    @Column(name ="floorarea")
    private Long floorArea;

    @Column(name ="managername")
    private String managerName;

    @Column(name ="managerphone")
    private String managerphone;

    @Column(name ="rentprice")
    private Float rentPrice;

    @Column(name = "servicefee")
    private Float servicefee;

    @Column(name = "carfee")
    private Float carfee;

    @Column(name = "motofee")
    private Float motofee;

    @Column(name ="waterfee")
    private Float waterfee;

    @Column(name = "electricityfee")
    private Float electricityfee;


    @Column(name = "deposit")
    private String deposit;

    @Column(name="TotalNumberOfAvailableRooms")
    private Long totalnumberofavailablerooms;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name ="userid")
    private UserEntity userid;

    @OneToMany(mappedBy = "building", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<ImageEntity> images = new ArrayList<>();
}