package com.anand.springproject.service.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.coyote.http2.Http2Protocol;
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
    private int httpServerPort;

    @Value("${server.port:8443}")
    private int httpsServerPort;

    @Value("${server.http2.enabled:true}")
    private boolean http2Enabled;

    @Value("${server.tomcat.threads.max}")
    private String maxThreads;

    @Value("${server.tomcat.threads.min-spare}")
    private String minSpareThreads;

    @Value("${server.tomcat.accept-count}")
    private String acceptCount;

    @Value("${server.tomcat.connection-timeout}")
    private String connectionTimeout;

    @Value("${server.tomcat.keep-alive-timeout}")
    private int keepAliveTimeout;

    @Value("${server.tomcat.max-connections}")
    private String maxConnections;

    @Value("${server.max-http-header-size}")
    private String maxHttpHeaderSize;

    /**
     * For only connector
     */
//    @Bean
//    public ServletWebServerFactory servletContainer() {
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//        Connector connector = createConnector();
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

        Connector redirectConnector =  createConnector();
        redirectConnector.setRedirectPort(httpsServerPort);

        tomcat.addAdditionalTomcatConnectors(redirectConnector);
        return tomcat;
    }

    private Connector createConnector() {
        return new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL){{
            setScheme(HTTP);
            setPort(httpServerPort);
            setSecure(false);

            setProperty("maxThreads", maxThreads);
            setProperty("minSpareThreads", minSpareThreads);
            setProperty("acceptCount", acceptCount);
            setProperty("connectionTimeout", connectionTimeout);
            setProperty("maxConnections", maxConnections);
            setProperty("maxHttpHeaderSize", maxHttpHeaderSize);

            AbstractHttp11Protocol<?> httpHandler = ((AbstractHttp11Protocol<?>) getProtocolHandler());
            httpHandler.setMaxKeepAliveRequests(-1);
            httpHandler.setUseKeepAliveResponseHeader(true);
            httpHandler.setKeepAliveTimeout(keepAliveTimeout);

            if(http2Enabled) {
                addUpgradeProtocol(new Http2Protocol());
            }
        }};
    }
}