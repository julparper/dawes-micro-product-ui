package es.etg.dawes.micro.product.iu.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import es.etg.dawes.micro.product.iu.view.ModelAttribute;

import es.etg.dawes.micro.product.iu.model.Producto;
import es.etg.dawes.micro.product.iu.view.ThymView;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProductController {
    

    @GetMapping("/web/productos")
    public String listar(Model model) {
        // Consulto todos los productos y los meto en un atributo del modelo para poder acceder a ellos en la JSP.
        model.addAttribute(ModelAttribute.PRODUCT_LIST.getName(), getTestData());
        return ThymView.PRODUCT_LIST.getPath(); // Busca productos-lista.jsp
    }


    private List<Producto> getTestData(){

        List<Producto> lista = new ArrayList<>();
        for(int i=0 ; i<10; i++){
            lista.add(new Producto(i, "Nombre"+i, Double.valueOf(i)));
        }
        return lista;
    }
}
