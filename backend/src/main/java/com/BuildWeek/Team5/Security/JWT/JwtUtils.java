package com.BuildWeek.Team5.Security.JWT;

import com.BuildWeek.Team5.Security.Services.UtenteDetailsImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component

public class JwtUtils {

    // aggiungere le costanti legate al JWT
    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    // metodo per la creazione del JWT
    public String createJwtToken(Authentication authentication){

        UtenteDetailsImpl utentePrincipal = (UtenteDetailsImpl) authentication.getPrincipal();

        long expirationTime = Long.parseLong(jwtExpiration);

        return Jwts.builder()
                .setSubject(utentePrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // recupero della scadenza da JWT
    public Date getExpirationFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getExpiration();
    }

    // recupero dello username da JWT
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody().getSubject();
    }

    // validazione del token JWT
    public boolean validazioneJwtToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey()).build().parse(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // recupero della chiave
    public Key getKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecret));
    }
}
