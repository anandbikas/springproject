package com.anand.springproject.service.config;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorCustomizer {

    @Value("${server.tomcat.keep-alive-timeout}")
    private int keepAliveTimeout;

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainerCustomizer() {
        return new WebServerFactoryCustomizer<TomcatServletWebServerFactory>() {
            @Override
            public void customize(TomcatServletWebServerFactory factory) {
                factory.addConnectorCustomizers(new TomcatConnectorCustomizer() {
                    @Override
                    public void customize(Connector connector) {
                        AbstractHttp11Protocol<?> httpHandler = ((AbstractHttp11Protocol<?>) connector.getProtocolHandler());

                        httpHandler.setMaxKeepAliveRequests(-1);
                        httpHandler.setUseKeepAliveResponseHeader(true);
                        httpHandler.setKeepAliveTimeout(keepAliveTimeout);

                        httpHandler.setUseServerCipherSuitesOrder(true);
                        httpHandler.setSSLHonorCipherOrder(true);
                    }
                });
            }
        };
    }
}