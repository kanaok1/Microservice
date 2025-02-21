package ru.itmentor.crud.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import ru.itmentor.crud.service.AdminService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Value("${DEFOLT_USERNAME}")
    private String defoltUsername;

    @Value("${DEFOLT_PASSWORD}")
    private String defoltPassword;

    private static final String ADMIN_ROLE = "ADMIN";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/api/v1/admin/**").hasRole(ADMIN_ROLE)
                        .requestMatchers("/api/v1/user").hasAnyRole("USER", ADMIN_ROLE)
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(AdminService adminService) {
        UserDetails defoltUser = User.builder()
                .username(defoltUsername)
                .password(passwordEncoder().encode(defoltPassword))
                .roles(ADMIN_ROLE)
                .build();

        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(defoltUser);

        return username -> {
            try {
                return inMemoryUserDetailsManager.loadUserByUsername(username);
            } catch (UsernameNotFoundException e) {
                return adminService.findByUsername(username);
            }
        };
    }
}