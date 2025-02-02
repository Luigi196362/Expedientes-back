package com.uv.api_expedientes.jwt;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.uv.api_expedientes.Permisos.Permiso;
import com.uv.api_expedientes.Users.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

    public String getToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        if (userDetails instanceof User) {
            User user = (User) userDetails;

            // Guardamos el nombre del rol
            extraClaims.put("rol", user.getRol().getNombre());

            // Agrupamos los permisos por recurso
            Map<String, List<String>> permisosAgrupados = new HashMap<>();
            if (user.getRol().getPermisos() != null) {
                for (Permiso permiso : user.getRol().getPermisos()) {
                    String recurso = permiso.getRecurso().getNombre();
                    String accion = permiso.getAccion().getNombre();

                    permisosAgrupados.computeIfAbsent(recurso, k -> new ArrayList<>()).add(accion);
                }
            }
            extraClaims.put("permisos", permisosAgrupados);
        } else {
            extraClaims.put("roles", userDetails.getAuthorities());
        }

        return generateToken(extraClaims, userDetails);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails user) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername()) // El username se guarda en "sub"
                .setIssuedAt(new Date(System.currentTimeMillis()))
                // Token válido por 24 horas
                // .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                // Token válido por 30 segundos
                .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }
}
