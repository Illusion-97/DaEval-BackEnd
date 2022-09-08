package org.dawan.formations.tools;

import org.dawan.formations.models.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TokenTools {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String generateToken(Utilisateur user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("user_id", user.getId());
        claims.put("user_email", user.getEmail());
        String token = jwtTokenUtil.doGenerateToken(claims, user.getEmail());
        TokenSaver.tokensByEmail.put(user.getEmail(), token);
        return token;
    }

    public String getEmailFromToken(String token) {
        return jwtTokenUtil.getUsernameFromToken(token);
    }

    public boolean isTokenExpired(String token) {
        return jwtTokenUtil.isTokenExpired(token);
    }

    public <T> T getClaimFromToken(String claim, String token, Class<T> c) {
        return jwtTokenUtil.getAllClaimsFromToken(token).get(claim, c);
    }
}
