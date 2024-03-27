package com.nus.iss.tasktracker.util;

import com.nus.iss.tasktracker.dto.UserDTO;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    @Value("${task-tracker.jwt.secret:" + TaskTrackerConstant.JWT_MAGIC_WORD + "}")
    private String secret;

    @Value("${task-tracker.jwt.expirationTimeInMins:" + TaskTrackerConstant.JWT_EXPIRATION_MINUTES + "}")
    private int jwtTokenExpiryInMins;

    public void createJWT(UserDTO userDTO)  {

        String jwtToken = null;

        //FIXME - REPLACE DEPRECATED METHOD

        Claims claims = Jwts.claims().subject(userDTO.getUsername()).build();
        System.out.println("Claims: "+claims);

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS512.getJcaName());

        System.out.println("hmacKey: "+hmacKey);

        jwtToken = Jwts.builder()
                    .issuer(TaskTrackerConstant.JWT_ISSUER)
                    .claims(claims)
                    .claim("auth", userDTO.getUserRole())
                    .id(UUID.randomUUID().toString())
                    .issuedAt(Date.from(Instant.now()))
                    .expiration(Date.from(Instant.now().plus(jwtTokenExpiryInMins, ChronoUnit.MINUTES)))
                    .signWith(hmacKey)
                    .compact();

        System.out.println("JWT Token: "+jwtToken);

        userDTO.setAuthToken(jwtToken);
    }

    public String[] validateJWT(String jwtString){
        String[] subjectRoleValues = new String[2];

        //FIXME - REPLACE DEPRECATED APIs
        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS512.getJcaName());
        System.out.println("hmacKey: "+hmacKey);

        try {
            Jws<Claims> jwt = Jwts.parser()
                    .setSigningKey(hmacKey)
                    .build()
                    .parseClaimsJws(jwtString);
            System.out.println("jwt token: "+jwt);
            System.out.println("jwt token expiration: "+jwt.getPayload().getExpiration());
            subjectRoleValues[0] = jwt.getPayload().getSubject();
            System.out.println("jwt token subject / username: "+subjectRoleValues[0]);
            subjectRoleValues[1] = (String)jwt.getPayload().get("auth");
            System.out.println("jwt token auth: "+subjectRoleValues[1]);

        } catch(ExpiredJwtException expiredJwtException){
            System.out.println("Auth Token Expired: "+expiredJwtException.getMessage());
        } catch(io.jsonwebtoken.security.SignatureException signatureException){
            System.out.println("Signature validation failed: "+signatureException.getMessage());
        }

        return subjectRoleValues;
    }

}
