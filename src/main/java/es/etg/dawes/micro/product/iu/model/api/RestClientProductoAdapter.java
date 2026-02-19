package es.etg.dawes.micro.product.iu.model.api;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import es.etg.dawes.micro.product.iu.model.Producto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestClientProductoAdapter {

    private final RestClient restClient;

    public List<Producto> getAll() {
        return restClient.get().retrieve().body(new ParameterizedTypeReference<List<Producto>>(){});
    }

}