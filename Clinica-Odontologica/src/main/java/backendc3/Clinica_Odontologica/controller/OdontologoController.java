package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.model.Odontologo;
import backendc3.Clinica_Odontologica.service.OdontologoService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private final OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @GetMapping("/todos")

    public  String buscarOdontologoTodos(Model model){
        List<Odontologo> listaOdontologos = odontologoService.buscarOdontologos();
        model.addAttribute("odontologos", listaOdontologos);
        return "listaodontologos";

    
}}