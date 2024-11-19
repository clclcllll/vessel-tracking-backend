package main.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtTokenUtil {

    // 使用 Base64 编码的密钥
    // 使用 io.jsonwebtoken.security.Keys 生成符合 HS512 的安全密钥
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long EXPIRATION_TIME = 86400000; // 1天

    // 生成 Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用合法密钥签名
                .compact();
    }

    // 验证 Token 是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token); // 使用合法密钥验证
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 从 Token 中获取用户名
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
