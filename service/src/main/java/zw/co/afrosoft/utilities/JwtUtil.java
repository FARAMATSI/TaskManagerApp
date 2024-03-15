package zw.co.afrosoft.utilities;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET ="your-secret-key";
    private static final long EXP_TIME =864 * 1000;

    public static String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXP_TIME))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }
}
