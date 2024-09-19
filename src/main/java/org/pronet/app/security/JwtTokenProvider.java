package org.pronet.app.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value(value = "${app.secret-key}")
    private String SECRET_KEY;

    @Value(value = "${app.expires-in}")
    private Long EXPIRES_IN;

    public String generateToken(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts
                .builder()
                .setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    public Boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Boolean isTokenExpired(String token) {
        Date expiration = Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
