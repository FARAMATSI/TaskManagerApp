package zw.co.afrosoft.utilities;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException, java.io.IOException {
        //token will be sent in the header of the request

        String authHeader=request.getHeader("Authorization");
        String token = null;

        if(authHeader != null && authHeader.startsWith("Bearer")){
            token = authHeader.substring(7); // cuts off Bearer & left with  JWT
        }
        //validate token
        if(token!=null && JwtUtil.validateToken(token)){
            String username =JwtUtil.extractUsername(token);
            //put the validated username into the security context holder
            Authentication auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request,response); //passes the request to the next filter
    }
}
