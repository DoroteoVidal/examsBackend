package com.system.exams.systemexamsbackend.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.json.JSONObject;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
    private String SECRET_KEY = "examportal";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /*private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }*/
    private Claims extractAllClaims(String token) {
    String[] tokenParts = token.split("\\."); // Dividir el token en sus partes: header, payload, signature
    String encodedPayload = tokenParts[1]; // El payload está en la segunda parte

    // Decodificar el payload codificado en Base64
    byte[] decodedBytes = Base64.getDecoder().decode(encodedPayload);
    String decodedPayload = new String(decodedBytes, StandardCharsets.UTF_8);

    // Convertir el payload a un objeto JSON (puedes usar librerías como Gson o Jackson)
    JSONObject payloadJson = new JSONObject(decodedPayload);

    // Puedes realizar validaciones o manipular los claims según tus necesidades
    Claims claims = Jwts.claims(payloadJson.toMap());

    return claims;
}

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
