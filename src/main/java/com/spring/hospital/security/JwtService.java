package com.spring.hospital.security;

import com.spring.hospital.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY ="38792F423F4428472B4B6250655368566D597133743677397A24432646294840";
public String extractUsername(String token){
    return extractClaim(token, Claims::getSubject);
}

public <T> T extractClaim (String token, Function<Claims,T> claimsRevolver){
    final  Claims claims = extractAllClaims(token);
    return  claimsRevolver.apply(claims);
}
public String generateToken(
        Map<String, Object> extraClaims,
        UserDetails userDetails
){
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis()+ 1000 * 60 * 60 * 72 ))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
}
private Claims extractAllClaims(String token){
    return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
}
    public List<String> getUserRoles(User user) {
        return Collections.singletonList(user.getUserRole().name());
    }

public String generateToken(UserDetails userDetails){
    User user =(User) userDetails;
    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", getUserRoles(user));
    return generateToken(claims,userDetails);
}

public boolean isTokenValid (String token, UserDetails userDetails){
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
}

    private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
    return extractClaim(token,Claims::getExpiration);
    }

    private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
    }

}
