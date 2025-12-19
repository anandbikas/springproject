package com.anand.springproject.service.security.config;
import com.anand.springproject.service.security.authentication.ServerAuthenticationEntryPoint;
import com.anand.springproject.service.security.authentication.SSLAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 * <a href="https://www.baeldung.com/spring-deprecated-websecurityconfigureradapter">...</a>
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Value("${server.ssl.client-auth:none}")
    private String serverSslClientAuth;

    @Value("${server.ssl.enabled:false}")
    private boolean serverSslEnabled;

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
        http
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                        httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(serverAuthenticationEntryPoint()))
                .csrf(Customizer.withDefaults())
                .formLogin(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
        ;

        if(serverSslEnabled && serverSslClientAuth.equalsIgnoreCase("want")){

            // Either validate the client cert is presented using custom filter
            // Or use .x509 true authenticator.
            http
                .addFilterAfter(new SSLAuthenticationFilter(), SecurityContextPersistenceFilter.class);

//             http
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated()
//            .and()
//                .x509()
//                    .subjectPrincipalRegex("CN=(.*?)(?:,|$)")
//                    .userDetailsService(userDetailsService());
        }
        return http.build();
    }

    /**
     * userDetailsService to fetch username from client ssl certificate
     *
     * @return
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
//                if (username.equals("Bikas Anand")) {
                    return new User(username, "",
                            AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
//                }
//                return null;
            }
        };
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/error")
                .requestMatchers("/status")
                .requestMatchers("/assets/**")
                .requestMatchers("/actuator/**")
                .requestMatchers("/webjars/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationManagerBeanDefinitionParser.NullAuthenticationProvider();
    }

    @Bean
    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return new ServerAuthenticationEntryPoint();
    }
}