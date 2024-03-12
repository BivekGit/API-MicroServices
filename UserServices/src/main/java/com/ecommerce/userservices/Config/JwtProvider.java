package com.ecommerce.userservices.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtProvider {
    static SecretKey secretKey = Keys.hmacShaKeyFor(JWTConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth) {

        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);
        String jtw = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + JWTConstant.EXPIRATION_TIME))
                .claim("authorities", roles)
                .claim("email", auth.getName())
                .signWith(secretKey)
                .compact();

        return jtw;
    }

    public static String getEmailFromJwt(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return String.valueOf(claims.get("email"));
    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }

}

