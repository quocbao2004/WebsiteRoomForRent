package com.javaweb.WebsiteRoomForRent.components;

import com.javaweb.WebsiteRoomForRent.customexceptions.InvalidParamException;
import com.javaweb.WebsiteRoomForRent.entities.TokenEntity;
import com.javaweb.WebsiteRoomForRent.entities.UserEntity;
import com.javaweb.WebsiteRoomForRent.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {
    @Value("${jwt.expiration}")
    private int expiration; // Thời gian hết hạn token (giây)

    @Value("${jwt.secretKey}")
    private String secretKey;

    private final TokenRepository tokenRepository;

    public String generateToken(UserEntity userEntity) throws Exception {
        // Khai báo claims
        Map<String, Object> claims = new HashMap<>();
        claims.put("phoneNumber", userEntity.getPhone());

        try {
            String jwtId = UUID.randomUUID().toString();
            String token = Jwts.builder()
                    .setClaims(claims)
                    .setSubject(userEntity.getPhone())
                    .setId(jwtId)
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                    .compact();

            return token;
        } catch (Exception e) {
            throw new InvalidParamException("Cannot create JWT token, error: " + e.getMessage());
        }
    }

    private Key getSignInKey() {
        byte[] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = this.extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenExpired(String token) {
        Date expirationDate = this.extractClaim(token, Claims::getExpiration);
        return expirationDate.before(new Date());
    }

    public String extractPhoneNumber(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String phoneNumber = extractPhoneNumber(token);

        Optional<TokenEntity> optionalTokenEntity = tokenRepository.findByToken(token);

        if (optionalTokenEntity.isEmpty() || optionalTokenEntity.get().isRevoked()) {
            return false;
        }

        return (phoneNumber.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    public String extractCustomClaim(String token, String claimKey) {
        Claims claims = this.extractAllClaims(token);
        return claims.get(claimKey, String.class);
    }
}
