package com.fontys.s3itproject.business.impl;

import com.fontys.s3itproject.business.AccessTokenDecoder;
import com.fontys.s3itproject.business.AccessTokenEncoder;
import com.fontys.s3itproject.business.exception.InvalidAccessTokenException;
import com.fontys.s3itproject.dto.AccessTokenDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

//    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//    }

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessTokenDTO accessTokenDTO) {
        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessTokenDTO.getRoles())){
            claimsMap.put("roles", accessTokenDTO.getRoles());
        }
        if (accessTokenDTO.getEmployeeId() != null){
            claimsMap.put("employeeID", accessTokenDTO.getEmployeeId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessTokenDTO.getSubject())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessTokenDTO decode(String accessTokenEncoded) {
        try {
            Jwt jwt = Jwts.parserBuilder().setSigningKey(key).build().parse(accessTokenEncoded);
            Claims claims = (Claims) jwt.getBody();

            List<String> roles = claims.get("roles", List.class);

            return AccessTokenDTO.builder()
                    .subject(claims.getSubject())
                    .roles(roles)
                    .employeeId(claims.get("employeeID", Long.class))
                    .build();
        } catch (JwtException e){
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }
}
