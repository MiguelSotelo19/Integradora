package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Cts_users;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DaoCts_users implements DaoRepository<Cts_users>{
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;

    @Override
    public List<Cts_users> findAll(Long id) {
        List<Cts_users> rels = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL v_contratos_us('');";
            cstm=conn.prepareCall(query);
            rs= cstm.executeQuery();

            while(rs.next()){
                Cts_users rel = new Cts_users();
                rel.setId(rs.getString("id_contrato"));
                rel.setFecha(rs.getString("fecha_evento"));
                rel.setHoras(rs.getString("horas_apartadas"));
                rel.setDireccion(rs.getString("direccion"));
                rel.setNombre(rs.getString("nombre_completo"));
                rel.setTelefono(rs.getString("telefono"));
                rel.setCabina(rs.getString("tipo_cabina"));
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
    public List<Cts_users> findAll() {
        return null;
    }

    @Override
    public Cts_users findOne(Long id, String id2) {
        return null;
    }

    @Override
    public boolean save(Cts_users object) {
        return false;
    }

    @Override
    public boolean update(Cts_users object, String aux) {
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
