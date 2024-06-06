package backendc3.Clinica_Odontologica.service;

import backendc3.Clinica_Odontologica.dao.TurnoDAOLISTA;
import backendc3.Clinica_Odontologica.dao.iDao;
import backendc3.Clinica_Odontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao= new TurnoDAOLISTA();
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }
    public List<Turno> buscarTodos(){
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        return turnoiDao.buscarPorId(id);
    }
}
