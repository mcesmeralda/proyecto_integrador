package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.model.Paciente;
import backendc3.Clinica_Odontologica.service.PacienteService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController  //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista



@RequestMapping("/pacientes")

public class PacienteController {

    private final PacienteService pacienteService;



    public PacienteController() {
        pacienteService = new PacienteService();
    }

    //ahora vienen todos los metodos que nos permitan actuar como intermediarios.
    @GetMapping("/buscarPorCorreo")
    public String buscarPacientePorCorreo(Model model, @RequestParam("email") String email) {

        Paciente paciente = pacienteService.buscarPorEmail(email);
        model.addAttribute("nombre", paciente.getNombre());
        model.addAttribute("apellido", paciente.getApellido());
        model.addAttribute("numeroMatricula",paciente.getOdontologo().getNumeroMatricula());
        return "index";

    }
     @GetMapping("/todos")
    public  String buscarPacientesTodos(Model model) {
         List<Paciente> listaPacientes = pacienteService.buscarPaciente();
         model.addAttribute("pacientes", listaPacientes);
         return "listapacientes";
     }


    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);
    }

    @PutMapping("/update")
    public String actualizarPaciente(@RequestBody Paciente paciente) {

        Paciente pacienteBuscado = pacienteService.buscarPorID(paciente.getId());
        if (pacienteBuscado != null) {
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        } else {
            return "paciente no encontrado";
        }

    }

    @GetMapping("/{id}")
    public Paciente buscarPorPaciente(@PathVariable Integer id) {
        return pacienteService.buscarPorID(id);
    }
//
}