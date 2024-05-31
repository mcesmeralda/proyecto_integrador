package backendc3.Clinica_Odontologica.dao;

import backendc3.Clinica_Odontologica.model.Paciente;

import java.util.List;

public interface iDao<T> {
    //todo el crud
    T guardar(T t);
    T buscarPorId(Integer id);
    void eliminar(Integer id);
    void actualizar(T t);

    List<T> buscarTodos();

    T buscarPorString(String string);
}
