package zw.co.afrosoft.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {
    private static final SecretKey SECRET = Keys.secretKeyFor(SignatureAlgorithm.HS512);;
    private static final long EXP_TIME =864000;

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
    public static String extractUsername(String token){
        return Jwts.                  parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public static boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Signature Exception");
            {
                System.out.println("Expired token exception");
            }
            return false;
        }
    }
}