package com.anand.springproject.service.security.config;
import com.anand.springproject.service.security.authentication.ServerAuthenticationEntryPoint;
import com.anand.springproject.service.security.authentication.SSLAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${server.ssl.client-auth:none}")
    private String serverSslClientAuth;

    @Value("${server.ssl.enabled:false}")
    private boolean serverSslEnabled;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .exceptionHandling()
                .authenticationEntryPoint(serverAuthenticationEntryPoint())
        .and()
                .csrf().disable()
                .formLogin()
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

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/error")
                .antMatchers("/status");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(
                new AuthenticationManagerBeanDefinitionParser.NullAuthenticationProvider());
    }

    @Bean
    public ServerAuthenticationEntryPoint serverAuthenticationEntryPoint() {
        return new ServerAuthenticationEntryPoint();
    }
}