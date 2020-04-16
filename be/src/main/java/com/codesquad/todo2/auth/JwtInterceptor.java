package com.codesquad.todo2.auth;

import com.codesquad.todo2.exception.TokenNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JwtInterceptor implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JwtInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.debug("[*] preHandle -------------------------------------");
        String jwt = request.getHeader("Authorization");

        if (jwt != null) {
            String token = jwt.replace("Bearer ", "");
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtUtil.secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String name = claims.get("name", String.class);
            request.setAttribute("name", name);

            log.debug("name : {}", name);

            return true;
        }
        throw new TokenNotFoundException("토큰값이 존재하지 않습니다.");
    }

}
