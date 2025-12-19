package com.anand.springproject.client.config;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.ClientTlsStrategyBuilder;
import org.apache.hc.client5.http.ssl.DefaultHostnameVerifier;
import org.apache.hc.client5.http.ssl.TlsSocketStrategy;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.nio.file.Paths;

/**
 * <a href="https://www.madhur.co.in/blog/2020/05/16/http-timeouts.html">...</a>
 */
@Configuration
public class SslRestTemplateConfig {

    @Value("${client.keystore.file:ssl/client_keystore.p12}")
    private String keyStoreFile;

    @Value("${client.keystore.password:password}")
    private String keyStorePassword;

    @Value("${client.keystore.alias:client.bikas.anand}")
    private String privateKeyAlias;

    @Value("${servers.truststore.file:ssl/servers_truststore.p12}")
    private String trustStoreFile;

    @Value("${servers.truststore.password:password}")
    private String trustStorePassword;

    @Bean
    @Qualifier("sslRestTemplate")
    RestTemplate restTemplate() throws Exception {

        SSLContext sslContext = SSLContextBuilder.create()
                .loadKeyMaterial(
                        new File(Paths.get(keyStoreFile).isAbsolute() ? keyStoreFile : getClass().getClassLoader().getResource(keyStoreFile).getPath()),
                        keyStorePassword.toCharArray(), keyStorePassword.toCharArray(), (aliases, socket) -> privateKeyAlias)
                .loadTrustMaterial(
                        new File(Paths.get(trustStoreFile).isAbsolute() ? trustStoreFile : getClass().getClassLoader().getResource(trustStoreFile).getPath()),
                        trustStorePassword.toCharArray())
                .build();

        TlsSocketStrategy tlsStrategy = (TlsSocketStrategy) ClientTlsStrategyBuilder.create()
                .setSslContext(sslContext)
                .setTlsVersions(TLS.V_1_2, TLS.V_1_3)
                .setHostnameVerifier(new DefaultHostnameVerifier())
                .build();

        ConnectionConfig cc = ConnectionConfig.custom()
                .setConnectTimeout(Timeout.ofSeconds(5)) // Connection establishment timeout
                .setSocketTimeout(Timeout.ofSeconds(30)) // Inactivity timeout between data packets
                .build();

        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setDefaultConnectionConfig(cc)
                .setTlsSocketStrategy(tlsStrategy)
                .build();

        RequestConfig rc = RequestConfig.custom()
                .setConnectionRequestTimeout(Timeout.ofSeconds(10)) // Timeout to get a connection from the pool
                .setResponseTimeout(Timeout.ofSeconds(60)) // Timeout for the entire response
                .build();

        HttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultRequestConfig(rc)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}
