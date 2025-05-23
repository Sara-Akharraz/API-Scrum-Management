package com.apiscrum.apiscrum.Security.Config;

import com.apiscrum.apiscrum.Security.JWT.JwtFilter;
import com.apiscrum.apiscrum.Security.JWT.JwtUtil;
import com.apiscrum.apiscrum.Security.Service.AuthUserDetailsServiceImpl;
import com.apiscrum.apiscrum.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuthUserDetailsServiceImpl authUserDetailsService;
    private final JwtUtil jwtUtil;

    public SecurityConfig(AuthUserDetailsServiceImpl authUserDetailsService, JwtUtil jwtUtil) {
        this.authUserDetailsService = authUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return authUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter(jwtUtil, authUserDetailsService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login","/error").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-resources/**").permitAll()
                        .requestMatchers("/webjars/**").permitAll()
                        .requestMatchers("/api/users/**").permitAll()

                        // Developer: tasks
                        .requestMatchers(HttpMethod.GET, "/api/tasks/**")
                        .hasAuthority(Role.DEVELOPER.name())

                        // Scrum Manager: test_acceptance, sprints, sprintbacklogs
                        .requestMatchers(HttpMethod.GET, "/api/acceptance_tests/**", "/api/sprint/**", "/api/sprintBackLog/**")
                        .hasAnyAuthority(Role.SCRUM_MANAGER.name())

                        // Product Manager: user stories, product backlogs, epics, projects
                        .requestMatchers(HttpMethod.GET, "/api/userStory/**", "/api/productBacklog/**", "/api/epic/**", "/api/project/**","/api/acceptance_tests/**")
                        .hasAuthority(Role.PRODUCT_OWNER.name())


                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
