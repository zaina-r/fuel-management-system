package org.example.fuel_management_system.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.example.fuel_management_system.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String STATIC_KEY= "71bd01b02c58bb607257b1f5dcac69db2f3f3777b62a99602c6a60aaef4bd923";

    @Autowired
    private HttpServletRequest request;

    public String extractJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        throw new IllegalArgumentException("JWT token is missing or malformed");
    }



    public String generateToken(UserAccount userAccount){
        return Jwts
                .builder()
                .subject(userAccount.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60*24))
                .signWith(getSignKey())
                .compact();
    }

    private static  SecretKey getSignKey() {
        byte[] keyBytes= Decoders.BASE64URL.decode(STATIC_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getTokenFromHeader() {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null;
    }


    public boolean isValidate(String token, UserDetails userDetails){
        String username=extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

        public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public  Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public    <T> T extractClaim(String token, Function<Claims,T>resolver) {
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);

    }

    public  Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
