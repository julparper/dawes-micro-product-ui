package es.etg.dawes.micro.product.iu.config;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.DefaultClientTlsStrategy;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.TrustSelfSignedStrategy;
import org.apache.hc.core5.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    //Recupero el valor del fichero application.properties
    @Value("${restclient.productos.baseurl}")
    private String productosBaseUrl;


    // Asumimos que tienes el truststore en src/main/resources
    @Value("${gateway.ssl.trust-store:classpath:truststore.p12}")
    private Resource trustStoreResource;

    @Value("${gateway.ssl.trust-store-password:password123}")
    private String trustStorePassword;


    @Bean
    public RestClient productoRestClient() throws Exception {
        
        // 1. Cargar el TrustStore (donde está tu cert.pem convertido)
        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(trustStoreResource.getInputStream(), trustStorePassword.toCharArray());

        // 2. Crear el contexto SSL que confía en nuestro material
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(trustStore, new TrustSelfSignedStrategy())
                .build();

        // 3. Crear el HttpClient de Apache (Client 5)
        // Usamos NoopHostnameVerifier para evitar errores si el cert no dice "localhost"
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                        .setTlsSocketStrategy(new DefaultClientTlsStrategy(sslContext, NoopHostnameVerifier.INSTANCE))
                        .build())
                .build();

        // 4. Construir el RestClient usando la factoría de Apache
        return RestClient.builder()
                .baseUrl(productosBaseUrl)
                .requestFactory(new HttpComponentsClientHttpRequestFactory(httpClient))
                .build();
    }
    
}
