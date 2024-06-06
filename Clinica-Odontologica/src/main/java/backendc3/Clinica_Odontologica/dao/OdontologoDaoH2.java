package backendc3.Clinica_Odontologica.dao;

import backendc3.Clinica_Odontologica.model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OdontologoDaoH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDaoH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS (NUMERO_MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET NUMERO_MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY_NUMERO_MATRICULA="SELECT * ODONTOLOGOS WHERE NUMERO_MATRICULA=?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("Iniciando la operación de guardar un odontólogo");
        Connection connection = null;
        try {
            connection = BD.getConnection();
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1, odontologo.getNumeroMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.executeUpdate();

            ResultSet rs = psInsert.getGeneratedKeys();
            if (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }


            logger.info("Odontólogo guardado con éxito: " + odontologo);
        } catch (SQLException e) {
            logger.error("Error al guardar el odontólogo: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return odontologo;
    }


    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando la operacion de buscado de un paciente con id : "+id);
        Connection connection= null;
        Odontologo odontologo= null;


        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSElectOne.setInt(1,id);
            ResultSet rs= psSElectOne.executeQuery();

            if(rs.next()){

                Odontologo odontologo1 = new Odontologo(rs.getInt("ID"), rs.getString("NUMERO_MATRICULA"), rs.getString("NOMBRE"), rs.getString("APELLIDO"));
            }

            logger.info("Odontólogos buscado con exito por id");
        }catch (Exception e){
            logger.error(e.getMessage());
        }


        return odontologo;
    }

    @Override
    public void eliminar(Integer id) {
        logger.info("iniciando las operaciones de: ");
        Connection connection= null;
        try{
            connection= BD.getConnection();

        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }


    @Override
    public void actualizar(Odontologo odontologo) {
        logger.warn("iniciando las operaciones de actualizacion de un odontologo con id : "+ odontologo.getId());
        Connection connection= null;

        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getNombre());
            psUpdate.setString(2, odontologo.getApellido());
            psUpdate.setString(3, odontologo.getNumeroMatricula());
            psUpdate.setInt(4, odontologo.getId());
            logger.info("Odontólogo actualizado con éxito");
            psUpdate.executeUpdate();


        }catch (Exception e){
            logger.error(e.getMessage());
        }


    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("Iniciando la operación de listar todos los odontólogos");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();
        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQL_SELECT_ALL);

            while (rs.next()) {
                Odontologo odontologo = new Odontologo(rs.getInt("ID"), rs.getString("NUMERO_MATRICULA"), rs.getString("NOMBRE"), rs.getString("APELLIDO"));
                odontologos.add(odontologo);
            }

            logger.info("Odontólogos listados con éxito");
        } catch (Exception e) {
            logger.error("Error al listar los odontólogos: " + e.getMessage());
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                logger.error("Error al cerrar la conexión: " + e.getMessage());
            }
        }
        return odontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        logger.info("iniciando las operaciones de: ");
        Connection connection= null;
        try{
            connection= BD.getConnection();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}