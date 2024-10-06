package com.javaweb.WebsiteRoomForRent.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="building")
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
    private Long rentPrice;

    @Column(name = "servicefee")
    private Long servicefee;

    @Column(name = "carfee")
    private Long carfee;

    @Column(name = "motofee")
    private  Long motofee;

    @Column(name ="waterfee")
    private Long waterfee;

    @Column(name = "electricityfee")
    private Long electricityfee;


    @Column(name = "deposit")
    private String deposit;

    @Column(name="TotalNumberOfAvailableRooms")
    private Long totalnumberofavailablerooms;

    @Column
    private String description;

    @ManyToOne
    @JoinColumn(name ="userid")
    private UserEntity userid;
}