package es.etg.dawes.micro.product.iu.controller;


import java.sql.Time;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestUtilController {
    

     @GetMapping("/api/saludar")
    public String saludar(Model model){
        System.out.println("get");
        return "Hola";
    }

    @GetMapping("/api/hora")
    public String getHora(Model model){
        return new Time(System.currentTimeMillis()).toString();
    }
}
