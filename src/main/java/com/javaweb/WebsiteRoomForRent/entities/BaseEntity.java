package com.javaweb.WebsiteRoomForRent.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

    @Column(name = "createddate")
    @CreatedDate
    private LocalDateTime createdDate;

    @Column(name = "createdby")
    @CreatedBy
    private String createdBy;

    @Column(name = "updateddate")
    @LastModifiedDate
    private LocalDateTime updatedDate;

    @Column(name = "updatedby")
    @LastModifiedBy
    private String updatedBy;
}
