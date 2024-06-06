package backendc3.Clinica_Odontologica.controller;

import backendc3.Clinica_Odontologica.dao.OdontologoDaoH2;
import backendc3.Clinica_Odontologica.dao.PacienteDAOH2;
import backendc3.Clinica_Odontologica.model.Odontologo;
import backendc3.Clinica_Odontologica.model.Paciente;
import backendc3.Clinica_Odontologica.model.Turno;
import backendc3.Clinica_Odontologica.service.OdontologoService;
import backendc3.Clinica_Odontologica.service.PacienteService;
import backendc3.Clinica_Odontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService= new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorID(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        }else{
            return ResponseEntity.badRequest().build();
        }

    }
    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
}
