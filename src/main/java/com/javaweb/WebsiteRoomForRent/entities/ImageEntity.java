package com.javaweb.WebsiteRoomForRent.entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Table(name = "ImageData")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter

public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_url",length = 300, nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building", nullable = false)
    private BuildingEntity building;
}