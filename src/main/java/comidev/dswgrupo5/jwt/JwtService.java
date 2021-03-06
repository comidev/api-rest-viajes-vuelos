package comidev.dswgrupo5.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import comidev.dswgrupo5.exceptions.unauthorized.JwtException;

@Service
public class JwtService {
    @Value("${app.jwt-secret}")
    private String SECRET;
    @Value("${app.jwt-expiration-in-second}")
    private String EXPIRES_IN_SECOND;
    private final String USER = "user";
    private final String ROLES = "roles";
    private final String BEARER = "Bearer ";
    private final String ISSUER = "comidev";

    public String createToken(String user, List<String> roles) {
        return createToken(user, roles, 0);
    }

    public String createRefreshToken(String user, List<String> roles) {
        return createToken(user, roles, 1800);
    }

    private String createToken(String user, List<String> roles, long addExpiresInSecond) {
        long expiresAtSecond = (Long.parseLong(EXPIRES_IN_SECOND) + addExpiresInSecond) * 1000;
        return JWT.create()
                .withIssuer(ISSUER) // empresa
                .withIssuedAt(new Date()) // fecha de creación
                .withNotBefore(new Date()) // valido desde
                .withExpiresAt(new Date(System.currentTimeMillis() + expiresAtSecond)) // tiempo
                .withClaim(USER, user)
                .withArrayClaim(ROLES, roles.toArray(new String[0]))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public boolean isBearer(String authorization) {
        return authorization != null
                && authorization.startsWith(BEARER)
                && authorization.split("\\.").length == 3;
    }

    public String username(String authorization) throws JwtException {
        return verify(authorization).getClaim(USER).asString();
    }

    private DecodedJWT verify(String authorization) throws JwtException {
        if (!isBearer(authorization)) {
            throw new JwtException("No es 'Bearer': " + authorization);
        }
        try {
            return JWT.require(Algorithm.HMAC256(SECRET))
                    .withIssuer(ISSUER).build()
                    .verify(authorization.substring(BEARER.length()));
        } catch (Exception e) {
            throw new JwtException("JWT incorrecto: " + e.getMessage());
        }
    }

    public List<GrantedAuthority> roles(String authorization) throws JwtException {
        return Stream.of(verify(authorization).getClaim(ROLES).asArray(String.class))
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }
}
