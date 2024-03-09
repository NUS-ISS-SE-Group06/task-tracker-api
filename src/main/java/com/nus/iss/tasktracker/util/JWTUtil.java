package com.nus.iss.tasktracker.util;

import com.nus.iss.tasktracker.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {

    @Value("${task-tracker.jwt.secret}")
    private String secret;

    @Value("${task-tracker.jwt.expirationTimeInMins}")
    private int jwtTokenExpiryInMins;

    public String createJWT() {
        String jwtString = "";

        //FIXME - CREATE NEW JWT

        return jwtString;
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
