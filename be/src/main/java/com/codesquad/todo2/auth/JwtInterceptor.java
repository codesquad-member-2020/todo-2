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

        if (request.getMethod().equals("OPTIONS")) {
                log.info("options 메서드는 통과");
                return true;
            }

        log.debug("[*] 토큰 체크 --------------------------------------------");
        String jwt = request.getHeader("Authorization");
	    log.debug("jwt string value {}:", jwt);

        if (jwt != null) {
            String token = jwt.replace("Bearer ", "");
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtUtil.secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            Long userId = claims.get("userId", Long.class);
            request.setAttribute("userId", userId);
            String name = claims.get("name", String.class);
            request.setAttribute("name", name);

            String userAgent = request.getHeader("User-Agent");

            if ( userAgent.contains("Mobile") ) { //web, ios
                userAgent = "iOS";
            } else {
                userAgent = "WEB";
            }

            request.setAttribute("userAgent", userAgent); //web, ios
            log.debug("name : {}, userAgent : {}", name, userAgent);

            return true;
        }
	log.debug("after if statement; falling to exception");
        throw new TokenNotFoundException("토큰값이 존재하지 않습니다.");
    }

}
