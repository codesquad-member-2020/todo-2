package com.codesquad.todo2.auth;

import com.codesquad.todo2.domain.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {
    public static String secretKey = "Todo2ProjectJWTToGetSecretKeyssssss";
    private static Key key = Keys.hmacShaKeyFor(secretKey.getBytes());
    private UserRepository userRepository;

    public static String createToken(Long userId, String userName) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();
        payloads.put("userId", userId);
        payloads.put("name", userName);

        String token = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 60 * 24)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

}

