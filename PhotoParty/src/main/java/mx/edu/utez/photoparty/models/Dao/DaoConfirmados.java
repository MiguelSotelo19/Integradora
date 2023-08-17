package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Confirmados;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoConfirmados implements DaoRepository<Confirmados>{
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;


    @Override
    public List<Confirmados> findAll(Long id) {
        List<Confirmados> rels = new ArrayList<>();
        try{
            conn= new MySQLConnEmp().connectEmp();
            String query = "CALL v_contratos(?);";
            cstm=conn.prepareCall(query);
            cstm.setLong(1, id);
            rs= cstm.executeQuery();

            while(rs.next()){
                Confirmados rel = new Confirmados();
                rel.setId(rs.getString("id_contrato"));
                rel.setFecha(rs.getString("fecha_evento"));
                rel.setInicio(rs.getString("hora_inicio"));
                rel.setHrs_final(rs.getString("hora_final"));
                rel.setDirec(rs.getString("direccion"));
                rel.setCabina(rs.getString("tipo_cabina"));
                rel.setEmp(rs.getInt("Empleados"));
                rels.add(rel);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return rels;
    }

    @Override
    public List<Confirmados> findAll() {
        return null;
    }

    @Override
    public Confirmados findOne(Long id, String id2) {
        return null;
    }

    @Override
    public boolean save(Confirmados object) {
        return false;
    }

    @Override
    public boolean update(Confirmados object, String aux) {
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {
        return false;
    }

    public void close(){
        try{
            if(conn != null) conn.close();
            if(cstm != null) cstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }
}
