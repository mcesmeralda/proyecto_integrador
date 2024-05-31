package backendc3.Clinica_Odontologica.service;

import backendc3.Clinica_Odontologica.dao.PacienteDAOH2;
import backendc3.Clinica_Odontologica.dao.iDao;
import backendc3.Clinica_Odontologica.model.Odontologo;
import backendc3.Clinica_Odontologica.model.Paciente;

import java.util.List;

public class PacienteService {
private iDao<Paciente> pacienteiDao;

    public PacienteService() {
        pacienteiDao= new PacienteDAOH2();
    }
    //metodos manuales
    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public Paciente buscarPorID(Integer id){
        return pacienteiDao.buscarPorId(id);
    }

    public Paciente buscarPorString(String email){
        return pacienteiDao.buscarPorString(email);
    }

    public void actualizarPaciente(Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }

    public Paciente buscarPorEmail(String email) {
        return pacienteiDao.buscarPorString(email);
    }

    public List<Paciente> buscarPaciente() {
        return buscarPaciente();
    }
}
