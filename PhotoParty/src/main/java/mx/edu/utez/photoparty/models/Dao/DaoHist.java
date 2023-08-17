package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Hist;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoHist implements DaoRepository<Hist> {
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;

    @Override
    public List<Hist> findAll(Long id) {
        List<Hist> hist =  new ArrayList<>();
        try{
            conn= new MySQLConnEmp().connectEmp();
            String query = "CALL v_cont_us(?, 0);";
            cstm = conn.prepareCall(query);
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            while(rs.next()){
                Hist rel = new Hist();
                rel.setId(rs.getString("id_contrato"));
                rel.setEvento(rs.getString("fecha_evento"));
                rel.setHoras(rs.getString("horas_apartadas"));
                rel.setDirec(rs.getString("direccion"));
                rel.setTel(rs.getString("telefono"));
                rel.setCabina(rs.getString("tipo_cabina"));
                rel.setUsuario(rs.getLong("id_usuario"));
                hist.add(rel);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return hist;
    }

    @Override
    public List<Hist> findAll() {
        return null;
    }

    @Override
    public Hist findOne(Long id, String id2) {
        return null;
    }

    @Override
    public boolean save(Hist object) {
        return false;
    }

    @Override
    public boolean update(Hist object, String aux) {
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {return false;}

    public List<Hist> pendientes(Long id) {
        List<Hist> pend =  new ArrayList<>();
        try{
            conn= new MySQLConnEmp().connectEmp();
            String query = "CALL v_cont_us(?, 1);";
            cstm = conn.prepareCall(query);
            cstm.setLong(1, id);
            rs = cstm.executeQuery();

            while(rs.next()){
                Hist rel = new Hist();
                rel.setId(rs.getString("id_contrato"));
                rel.setEvento(rs.getString("fecha_evento"));
                rel.setHoras(rs.getString("horas_apartadas"));
                rel.setDirec(rs.getString("direccion"));
                rel.setTel(rs.getString("telefono"));
                rel.setCabina(rs.getString("tipo_cabina"));
                rel.setUsuario(rs.getLong("id_usuario"));
                pend.add(rel);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return pend;
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
