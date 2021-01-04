package com.anand.springproject.service.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redirect http to https
 */
@Configuration
@ConditionalOnProperty(name="server.ssl.enabled", havingValue = "true")
public class ConnectorConfig {

    private static final String HTTP = "http";
    @Value("${http.server.port:8080}")
    int httpServerPort;

    @Value("${server.port:8443}")
    int httpsServerPort;

    /**
     * For only connector
     */
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme(HTTP);
//        connector.setPort(httpServerPort);
//        connector.setSecure(false);
//
//        tomcat.addAdditionalTomcatConnectors(connector);
//        return tomcat;
//    }

    /**
     * For connector with redirect to https
     *
     * @return
     */
    @Bean
    public ServletWebServerFactory servletContainer() {

        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) {
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(redirectConnector());
        return tomcat;
    }

    private Connector redirectConnector() {
        Connector connector =
                new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme(HTTP);
        connector.setPort(httpServerPort);
        connector.setSecure(false);
        connector.setRedirectPort(httpsServerPort);
        return connector;
    }
}