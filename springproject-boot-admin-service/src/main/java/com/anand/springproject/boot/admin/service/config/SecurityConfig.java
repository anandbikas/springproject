package com.anand.springproject.boot.admin.service.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
public class SecurityConfig {
    private final AdminServerProperties adminServer;
    private final SecurityProperties security;
    private final String contextPath;

    public SecurityConfig(AdminServerProperties adminServer, SecurityProperties security) {
        this.adminServer = adminServer;
        this.security = security;
        this.contextPath = adminServer.getContextPath();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(contextPath + "/assets/**").permitAll()
                        .requestMatchers(contextPath + "/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(fl -> fl
                        .loginPage(contextPath + "/login")
                        .successHandler(successHandler()))
                .logout(lc -> lc
                        .logoutUrl(contextPath + "/logout"))
                .httpBasic(Customizer.withDefaults())
                .csrf(cc -> cc
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .ignoringRequestMatchers(
                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, contextPath + "/instances"),
                                AntPathRequestMatcher.antMatcher(HttpMethod.DELETE, contextPath + "/instances/*"),
                                AntPathRequestMatcher.antMatcher(contextPath + "/actuator/**")))
                .rememberMe(rmc -> rmc
                        .key(UUID.randomUUID().toString())
                        .tokenValiditySeconds(1209600));

        return http.build();
    }

    // Required to provide UserDetailsService for "remember functionality"
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails userDetails = User
                .withUsername(security.getUser().getName())
                .password(passwordEncoder().encode(security.getUser().getPassword()))
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private SavedRequestAwareAuthenticationSuccessHandler successHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler(){{
                    setTargetUrlParameter("redirectTo");
                    setDefaultTargetUrl(adminServer.getContextPath() + "/");
                }};
    }
}