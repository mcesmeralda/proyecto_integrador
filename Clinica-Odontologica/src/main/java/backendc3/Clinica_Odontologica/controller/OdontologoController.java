package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.model.Odontologo;
import backendc3.Clinica_Odontologica.model.Paciente;
import backendc3.Clinica_Odontologica.service.OdontologoService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


}

    @PutMapping("/update")
    public String actualizarOdontologos(@RequestBody Odontologo odontologo) {

        Odontologo odontologoBuscado = odontologoService.buscarPorID(odontologo.getId());
        if (odontologoBuscado != null) {
            odontologoService.actualizarOdontologo(odontologo);
            return "odontologo actualizado con exito";
        } else {
            return "odontologo no encontrado";
        }

    }

    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }
    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos(){
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologo(@PathVariable Integer id) {
        return odontologoService.buscarPorID(id);
    }

}

