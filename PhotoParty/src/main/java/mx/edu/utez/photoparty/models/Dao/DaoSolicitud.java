package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Solicitud;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoSolicitud implements DaoRepository<Solicitud> {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cstm;
    private ResultSet rs;


    @Override
    public List<Solicitud> findAll(Long id) {
        List<Solicitud> solicitudes = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL all_solicitudes('');";
            pstm=conn.prepareStatement(query);
            rs= pstm.executeQuery();

            while(rs.next()){
                Solicitud solicitud = new Solicitud();
                solicitud.setId(rs.getLong("id_solicitud"));
                solicitud.setFecha(rs.getString("fecha_evento"));
                solicitud.setHr_inicio(rs.getString("hora_inicio"));
                solicitud.setHr_final(rs.getString("hora_final"));
                solicitud.setExtra(rs.getInt("horas_extra"));
                solicitud.setDireccion(rs.getString("direccion"));
                solicitud.setFk_id_cliente(rs.getString("fk_id_cliente"));
                solicitud.setFk_id_contrato(rs.getString("fk_id_contrato"));
                solicitudes.add(solicitud);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return solicitudes;
    }

    @Override
    public List<Solicitud> findAll() {
        return null;
    }

    @Override
    public Solicitud findOne(Long id, String id2) {
        try {
            conn = new MySQLConnEmp().connectEmp();
            String query = "CALL all_solicitudes(?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id2);
            rs = cstm.executeQuery();

            Solicitud solicitud = new Solicitud();
            if(rs.next()){
                solicitud.setId(rs.getLong("id_solicitud"));
                solicitud.setFecha(rs.getString("fecha_evento"));
                solicitud.setHr_inicio(rs.getString("hora_inicio"));
                solicitud.setHr_final(rs.getString("hora_final"));
                solicitud.setExtra(rs.getInt("horas_extra"));
                solicitud.setDireccion(rs.getString("direccion"));
                solicitud.setFk_id_cliente(rs.getString("fk_id_cliente"));
                solicitud.setFk_id_contrato(rs.getString("fk_id_contrato"));
            }
            return solicitud;
        }catch (SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean save(Solicitud object) {

        try{
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_solicitudes(?, ?, ?, ?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getFecha());
            cstm.setString(2, object.getHr_inicio());
            cstm.setString(3, object.getHr_final());
            cstm.setInt(4, object.getExtra());
            cstm.setString(5, object.getDireccion());
            cstm.setString(6, object.getFk_id_cliente());
            cstm.setString(7, object.getFk_id_contrato());
            return cstm.executeUpdate() > 0;
        }catch (SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName())
                    .log(Level.SEVERE, "Error al guardar"+e.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Solicitud object, String aux) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL actualizar_solicitud(?, ?, ?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getFk_id_contrato());
            cstm.setString(2, object.getFecha());
            cstm.setString(3, object.getHr_inicio());
            cstm.setString(4, object.getHr_final());
            cstm.setInt(5, object.getExtra());
            cstm.setString(6, object.getDireccion());

            /* (contrato_id , evento DATE, inicio ,final , extra , direccion )*/

            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean drag(Solicitud object) {

        try {
            conn = new MySQLConnection().connect();
            String query = "CALL evento_drop(?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getFk_id_contrato());
            cstm.setString(2, object.getFecha());
            cstm.setString(3, object.getHr_inicio());
            cstm.setString(4, object.getHr_final());

            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {
        return false;
    }

    public void close(){
        try{
            if(conn != null) conn.close();
            if(pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoSolicitud.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }
}
