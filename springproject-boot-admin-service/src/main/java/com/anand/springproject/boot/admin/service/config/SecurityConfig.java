package com.anand.springproject.boot.admin.service.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AdminServerProperties adminServer;
    private final SecurityProperties security;

    public SecurityConfig(AdminServerProperties adminServer, SecurityProperties security) {
        this.adminServer = adminServer;
        this.security = security;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers(this.adminServer.getContextPath() + "/assets/**").permitAll()
                .antMatchers(this.adminServer.getContextPath() + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(this.adminServer.getContextPath() + "/login")
                .successHandler(successHandler())
                .and()
            .logout()
                .logoutUrl(this.adminServer.getContextPath() + "/logout")
                .and()
            .httpBasic()
                .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                  new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances", HttpMethod.POST.toString()),
                  new AntPathRequestMatcher(this.adminServer.getContextPath() + "/instances/*", HttpMethod.DELETE.toString()),
                  new AntPathRequestMatcher(this.adminServer.getContextPath() + "/actuator/**"))
                .and()
            .rememberMe()
                .key(UUID.randomUUID().toString())
                .tokenValiditySeconds(1209600);

    }

    // Required to provide UserDetailsService for "remember functionality"
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(security.getUser().getName())
                .password("{noop}" + security.getUser().getPassword())
                .roles("USER");
    }

    private SavedRequestAwareAuthenticationSuccessHandler successHandler(){
        return new SavedRequestAwareAuthenticationSuccessHandler(){{
                    setTargetUrlParameter("redirectTo");
                    setDefaultTargetUrl(adminServer.getContextPath() + "/");
                }};
    }
}