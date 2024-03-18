package zw.co.afrosoft.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@EnableGlobalMethodSecurity(prePostEnabled=true)

@Configuration
public class SecurityConfiguration {
@Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
    UserDetails admin = User
            .withUsername("admin")
            .password(passwordEncoder.encode("1"))
            .roles("ADMIN","USER")
            .build();

    return new InMemoryUserDetailsManager(admin);
   }
   @Bean
    public PasswordEncoder passwordEncoder(){
      return new BCryptPasswordEncoder();
   }
   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
            .csrf(AbstractHttpConfigurer ::disable)
            .authorizeHttpRequests(auth->{
                auth.requestMatchers("/login").permitAll();
                auth.requestMatchers("/swagger-ui/**").permitAll();
                auth.requestMatchers("/v3/api-docs/**").permitAll();
                auth.anyRequest().authenticated();
            })
            .build();
    }
}
