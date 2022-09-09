package org.dawan.formations.interceptors;

import org.dawan.formations.exceptions.TokenException;
import org.dawan.formations.tools.JwtTokenUtil;
import org.dawan.formations.tools.TokenSaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor implements HandlerInterceptor {

    private static final Map<String, String[]> authorizeds = new HashMap<>();

    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public TokenInterceptor(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (!request.getMethod().equals("OPTIONS") && isInterceptedURI(request.getRequestURI(), request.getMethod())) {
            String headerAuth = request.getHeader("Authorization");
            if (headerAuth == null || headerAuth.trim().equals("") || headerAuth.length() < 7)
                throw new TokenException("Error : Invalid token !");
            String token = headerAuth.substring(7);
            if (jwtTokenUtil.isTokenExpired(token))
                throw new TokenException("Error : Expired token !");

            String email = jwtTokenUtil.getUsernameFromToken(token);
            if (!TokenSaver.getTokensByEmail().containsKey(email)
                    || !TokenSaver.getTokensByEmail().get(email).equals(token))
                throw new TokenException("Error : Unknown token !");
        }
        return true;
    }

    private boolean isInterceptedURI(String uri, String method) {
        return authorizeds.entrySet().stream().noneMatch(
                a -> a.getKey().equals(uri) &&
                        Arrays.asList(a.getValue()).contains(method));
    }
}
