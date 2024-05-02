package zw.co.afrosoft.Security;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetailsInterface {
    UserDetails loadUserByUsername(String username);
}
