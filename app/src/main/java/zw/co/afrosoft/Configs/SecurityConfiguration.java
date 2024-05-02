package zw.co.afrosoft.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import zw.co.afrosoft.utilities.JwtAuthenticationFilter;

//@EnableGlobalMethodSecurity(prePostEnabled=true)
@EnableWebSecurity
//@Security(methodSecurityEnabled = true)
@Configuration
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
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
                auth.requestMatchers("create-new-user","/supervisor/**").permitAll();
                auth.requestMatchers("/swagger-ui/**","/v3/api-docs/**","subtask/**","/assignees/**","task/**").permitAll();
                auth.requestMatchers("/v3/api-docs/**").permitAll();
                //auth.requestMatchers("/assignees/**").permitAll();

                auth.anyRequest().authenticated();
            })
            .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
            .build();
    }
    //add a bean that will handle jwt authentication filter. This bean will instantiate the filter
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
     return new JwtAuthenticationFilter();
    }

}
