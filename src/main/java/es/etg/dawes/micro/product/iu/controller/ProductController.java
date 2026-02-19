package es.etg.dawes.micro.product.iu.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.etg.dawes.micro.product.iu.model.Producto;
import es.etg.dawes.micro.product.iu.model.api.RestClientProductoAdapter;
import es.etg.dawes.micro.product.iu.model.auth.JwtService;
import es.etg.dawes.micro.product.iu.view.FragmentoContenido;
import es.etg.dawes.micro.product.iu.view.ModelAttribute;
import es.etg.dawes.micro.product.iu.view.ThymView;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ProductController {

    public static final String SESION_JWT_TOKEN = "JWT_TOKEN";
    public static final String USUARIO = "usuario";
    
    private final RestClientProductoAdapter restClient;
    private final JwtService jwtService;

    @GetMapping("/web/productos")
    public String listar(Model model, HttpSession sesion) {

        // Miro si el token está en la sesión del usuario, si no está lo genero y lo agrego a la sesión
        String token = (String) sesion.getAttribute(SESION_JWT_TOKEN);
        if (token == null) {
            token = jwtService.generarToken(USUARIO);
            sesion.setAttribute(SESION_JWT_TOKEN, token);
        }

        List<Producto> lista = restClient.getAll(token);

        //Indico el fragmento a cargar
        model.addAttribute(ModelAttribute.FRAGMENTO_CONTENIDO.getName(), FragmentoContenido.PRODUCT_LIST.getPath());
        model.addAttribute(ModelAttribute.PRODUCT_LIST.getName(), lista);
        return ThymView.PRODUCT_MAIN.getPath();
    }

    @GetMapping("/web/productos/nuevo")
    public String crear(Model model) {
        //Indico el fragmento a cargar
        model.addAttribute(ModelAttribute.FRAGMENTO_CONTENIDO.getName(), 
                FragmentoContenido.PRODUCT_FORM.getPath());
        model.addAttribute(ModelAttribute.SINGLE_PRODUCT.getName(), new Producto());
        return ThymView.PRODUCT_MAIN.getPath();
    }

    @PostMapping("/web/productos/nuevo")
    public String crearProducto(@RequestParam String nombre,
            @RequestParam double precio,
            Model model, HttpSession sesion){
        
        listar(model, sesion);
            
        return ThymView.PRODUCT_MAIN.getPath();
    }



    private List<Producto> getTestData(){
        List<Producto> lista = new ArrayList<>();
        for(int i=0 ; i<10; i++){
            lista.add(new Producto(i, "Nombre"+i, Double.valueOf(i)));
        }
        return lista;
    }
}
