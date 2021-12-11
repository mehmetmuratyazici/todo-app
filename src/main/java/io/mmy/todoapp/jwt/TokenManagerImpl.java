package io.mmy.todoapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenManagerImpl implements TokenManager{

    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final int validity = 1 * 60 * 1000;

    @Override
    public String generateToken(String username) {
        long cTimeMil = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(username)
                .setIssuer("mmy")
                .setIssuedAt(new Date(cTimeMil))
                .setExpiration(new Date(cTimeMil+validity))
                .signWith(key)
                .compact();
    }

    @Override
    public boolean tokenValidate(String token) {
        if(!getUsernameByToken(token).equals(""))
            return  isExpired(token);

        return false;
    }

    @Override
    public String getUsernameByToken(String token) {

        return getClaims(token).getSubject();
    }

    @Override
    public boolean isExpired(String token) {
        Claims claims = getClaims(token);

        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }
}
