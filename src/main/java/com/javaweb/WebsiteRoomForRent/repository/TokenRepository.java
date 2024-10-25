package com.javaweb.WebsiteRoomForRent.repository;

import com.javaweb.WebsiteRoomForRent.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {
    Optional<TokenEntity> findByToken(String token);
}