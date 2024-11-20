package com.example.EmployeeManagement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetails(){
        UserDetails adminDetails = User.withUsername("admin").password(passwordEncoder().encode("adminpass")).roles("ADMIN").build();
        UserDetails normalDetails = User.withUsername("normal").password(passwordEncoder().encode("normalpass")).roles("NORMAL").build();
        return new InMemoryUserDetailsManager(adminDetails,normalDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())  // Disabling CSRF (use with caution)
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/home/public").permitAll()  // Allow public access to /home/public
                                .requestMatchers("/home/admin").hasRole("ADMIN")
                                .requestMatchers("/home/normal").hasRole("NORMAL")
                                .anyRequest().authenticated()  // All other requests require authentication
                )
                .formLogin(login -> login  // Enable default form login
                        .permitAll());  // Allow everyone to access the login page
        return httpSecurity.build();
    }
}
