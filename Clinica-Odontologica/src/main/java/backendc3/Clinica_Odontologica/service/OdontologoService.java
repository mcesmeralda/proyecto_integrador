package backendc3.Clinica_Odontologica.service;

import backendc3.Clinica_Odontologica.dao.OdontologoDaoH2;
import backendc3.Clinica_Odontologica.dao.iDao;
import backendc3.Clinica_Odontologica.model.Odontologo;

import java.util.List;

public class OdontologoService{
    private iDao<Odontologo> OdontologoiDao;

    public OdontologoService() {
        OdontologoiDao= new OdontologoDaoH2();
    }
    //metodos manuales
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return OdontologoiDao.guardar(odontologo);
    }
    public List<Odontologo> buscarTodos() {
        return OdontologoiDao.buscarTodos();

    }

    public Odontologo buscarPorID(Integer id){
        return OdontologoiDao.buscarPorId(id);
    }

    public Odontologo buscarPorString(String string) {
    return buscarPorString(string);}

    public List<Odontologo> buscarOdontologos() {
        return buscarOdontologos();
    }
}
