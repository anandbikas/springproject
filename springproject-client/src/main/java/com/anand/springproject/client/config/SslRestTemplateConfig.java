package com.anand.springproject.client.config;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.nio.file.Paths;

@Configuration
public class SslRestTemplateConfig {

    @Value("${client.keystore.file:ssl/client_keystore.p12}")
    private String keyStoreFile;

    @Value("${client.keystore.password:password}")
    private String keyStorePassword;

    @Value("${servers.truststore.file:ssl/servers_truststore.p12}")
    private String trustStoreFile;

    @Value("${servers.truststore.password:password}")
    private String trustStorePassword;

    private static final int REQUEST_TIMEOUT_MS = 30000;

    @Bean
    @Qualifier("sslRestTemplate")
    RestTemplate restTemplate() throws Exception {

        SSLContext sslContext = new SSLContextBuilder()
                .loadKeyMaterial(
                        new File(Paths.get(keyStoreFile).isAbsolute() ? keyStoreFile :
                                getClass().getClassLoader().getResource(keyStoreFile).getPath()),
                                    keyStorePassword.toCharArray(), keyStorePassword.toCharArray()
                ).loadTrustMaterial(
                        new File(Paths.get(trustStoreFile).isAbsolute() ? trustStoreFile :
                                getClass().getClassLoader().getResource(trustStoreFile).getPath()),
                                    trustStorePassword.toCharArray()
                ).build();

        final RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(REQUEST_TIMEOUT_MS).build();

        final SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(
                sslContext,
                new String[] {"TLSv1.2"}, null,
                new DefaultHostnameVerifier()
                );
        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).setDefaultRequestConfig(requestConfig).build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
    }
}
