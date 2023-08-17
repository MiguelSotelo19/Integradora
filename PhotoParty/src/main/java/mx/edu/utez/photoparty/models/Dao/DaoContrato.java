package mx.edu.utez.photoparty.models.Dao;
import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.photoparty.models.Objects.Contrato;
import mx.edu.utez.photoparty.models.Objects.Horas;
import mx.edu.utez.photoparty.utils.MySQLConnClien;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DaoContrato implements DaoRepository<Contrato> {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cstm;
    private ResultSet rs;


    @Override
    public List<Contrato> findAll(Long id) {
        List<Contrato> contratos = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL all_contratos('');";
            pstm=conn.prepareStatement(query);
            rs= pstm.executeQuery();

            while(rs.next()){
                Contrato contrato = new Contrato();
                contrato.setId(rs.getString("id_contrato"));
                contrato.setActual(rs.getString("fecha_actual"));
                contrato.setTotal(rs.getDouble("monto_total"));
                contrato.setFk_id_cabina2(rs.getLong("fk_id_cabina"));
                contratos.add(contrato);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return contratos;
    }

    @Override
    public List<Contrato> findAll() {
        return null;
    }


    @Override
    public Contrato findOne(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL all_contratos(?);";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id2);
            rs = pstm.executeQuery();

            Contrato contrato = new Contrato();
            if(rs.next()){
                contrato.setId(rs.getString("id_contrato"));
                contrato.setActual(rs.getString("fecha_actual"));
                contrato.setTotal(rs.getDouble("monto_total"));
                contrato.setFk_id_cabina(rs.getString("nombre"));
            }
            return contrato;
        }catch (SQLException e){
            Logger.getLogger(DaoContrato.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean save(Contrato object) {
        try{
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_contrato(?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getId());
            cstm.setDouble(2, object.getTotal());
            cstm.setString(3, object.getFk_id_cabina());
            cstm.setString(4,object.getColor());
            return cstm.executeUpdate() > 0;
        }catch (SQLException e){
            Logger.getLogger(DaoContrato.class.getName())
                    .log(Level.SEVERE, "Error al guardar"+e.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Contrato object, String aux) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL update_contrato(?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getId());
            cstm.setDouble(2, object.getTotal());
            cstm.setString(3, object.getFk_id_cabina());
            cstm.setString(4, object.getColor());

            /*(contrato_id , monto , cabina ,color )*/

            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoContrato.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL eliminar_contrato(?);";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id2);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoContrato.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public List<Horas> horas(){
        List<Horas> hrs = new ArrayList<>();
        try{
            conn= new MySQLConnClien().connectClien();
            String query = "CALL horas();";
            cstm=conn.prepareCall(query);
            rs= cstm.executeQuery();

            while(rs.next()){
                Horas hr = new Horas();
                hr.setFecha(rs.getString("fecha_evento"));
                hr.setHrs_inicio(rs.getString("hora_inicio"));
                hr.setHrs_final(rs.getString("hora_final"));
                hrs.add(hr);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return hrs;
    }


    public void close(){
        try{
            if(conn != null) conn.close();
            if(pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoContrato.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }
}
