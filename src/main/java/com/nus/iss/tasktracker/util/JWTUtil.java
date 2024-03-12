package com.nus.iss.tasktracker.util;

import com.nus.iss.tasktracker.dto.UserDTO;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    @Value("${task-tracker.jwt.secret:" + TaskTrackerConstant.JWT_SECRET + "}")
    private String secret;

    @Value("${task-tracker.jwt.expirationTimeInMins:" + TaskTrackerConstant.JWT_EXPIRATION_MINS + "}")
    private int jwtTokenExpiryInMins;

    public String createJWT(String userName, String role)  {

        String jwtToken = null;

        //FIXME - REPLACE DEPRECATED METHOD

            jwtToken = Jwts.builder()
                    .issuer(TaskTrackerConstant.JWT_ISSUER)
                    .subject(userName)
                    .id(UUID.randomUUID().toString())
                    .issuedAt(Date.from(Instant.now()))
                    .expiration(Date.from(Instant.now().plus(jwtTokenExpiryInMins, ChronoUnit.MINUTES)))
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();

        System.out.println("JWT Token: "+jwtToken);

        return jwtToken;
    }

    public boolean validateJWT(String jwtString){
        boolean isTokenValid = true;
        // FIXME - CHECK WHETHER THE TOKEN IS VALID
        return isTokenValid;
    }

    public UserDTO decodeJWT(String jwtString){
        UserDTO userDTO=null;
        // FIXME - ADD CODE TO DECODE JWT
        return userDTO;
    }



}
