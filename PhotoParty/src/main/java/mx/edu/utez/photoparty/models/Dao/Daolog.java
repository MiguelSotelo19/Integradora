package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.log_contratos;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Daolog implements DaoRepository<log_contratos> {
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;

    @Override
    public List<log_contratos> findAll(Long id) {
        List<log_contratos> log =  new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL log(0, null);";
            cstm = conn.prepareCall(query);
            rs = cstm.executeQuery();

            while(rs.next()){
                log_contratos rel = new log_contratos();
                rel.setId(rs.getString("id_elim_contrato"));
                rel.setMonto(rs.getDouble("monto_total"));
                rel.setTelefono(rs.getLong("telefono"));
                rel.setFecha_act(rs.getString("fecha_realizacion"));
                rel.setFecha_ev(rs.getString("fecha_evento"));
                rel.setHoras(rs.getString("horas"));
                rel.setDireccion(rs.getString("direccion"));
                rel.setId_cliente(rs.getString("id_cliente"));
                rel.setNombre(rs.getString("nombre_completo"));
                rel.setCorreo(rs.getString("correo"));
                log.add(rel);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return log;
    }

    @Override
    public List<log_contratos> findAll() {
        return null;
    }

    @Override
    public log_contratos findOne(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL log(1, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id2);
            rs = cstm.executeQuery();

            log_contratos rel = new log_contratos();
            if(rs.next()){
                rel.setId(rs.getString("id_elim_contrato"));
                rel.setMonto(rs.getDouble("monto_total"));
                rel.setTelefono(rs.getLong("telefono"));
                rel.setFecha_act(rs.getString("fecha_realizacion"));
                rel.setFecha_ev(rs.getString("fecha_evento"));
                rel.setHoras(rs.getString("horas"));
                rel.setDireccion(rs.getString("direccion"));
                rel.setId_cliente(rs.getString("id_cliente"));
                rel.setNombre(rs.getString("nombre_completo"));
                rel.setCorreo(rs.getString("correo"));
            }
            return rel;
        }catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
            return null;
        } finally {
            close();
        }
    }

    @Override
    public boolean save(log_contratos object) {
        return false;
    }

    @Override
    public boolean update(log_contratos object, String aux) {
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
