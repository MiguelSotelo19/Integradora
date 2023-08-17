package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Contrato_usuario;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoContrato_usuario implements DaoRepository<Contrato_usuario> {
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;


    @Override
    public List<Contrato_usuario> findAll(Long id) { return null; }

    @Override
    public List<Contrato_usuario> findAll() {
        return null;
    }

    @Override
    public Contrato_usuario findOne(Long id, String id2) { return null; }

    @Override
    public boolean save(Contrato_usuario object) {
        try{
            conn = new MySQLConnEmp().connectEmp();
            String query = "CALL insertar_ctus(?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getFk_id_contrato());
            cstm.setLong(2, object.getFk_id_usuario());
            return cstm.executeUpdate() > 0;
        }catch (SQLException e){
            Logger.getLogger(DaoContrato_usuario.class.getName())
                    .log(Level.SEVERE, "Error al guardar"+e.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Contrato_usuario object, String aux) {
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {
        try {
            conn = new MySQLConnEmp().connectEmp();
            String query = "CALL delete_ct_us(?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setLong(1, id);
            cstm.setString(2, id2);
            return cstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoContrato_usuario.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }


    public void close(){
        try{
            if(conn != null) conn.close();
            if(cstm != null) cstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoContrato_usuario.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }
}
