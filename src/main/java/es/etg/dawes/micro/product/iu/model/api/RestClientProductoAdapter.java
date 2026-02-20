package es.etg.dawes.micro.product.iu.model.api;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import es.etg.dawes.micro.product.iu.model.Producto;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RestClientProductoAdapter {

    private final RestClient restClient;

    public List<Producto> getAll(String token) {

        return restClient.get().headers(h -> h.setBearerAuth(token)).retrieve().body(new ParameterizedTypeReference<List<Producto>>(){});
    }

    public void crear(String token, String nombre, double precio){
        Producto producto = new Producto(null, nombre, precio);
        ResponseEntity<Void> response = restClient.post().headers(h -> h.setBearerAuth(token)).contentType(APPLICATION_JSON).body(producto).retrieve().toBodilessEntity();
    }
}