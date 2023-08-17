package mx.edu.utez.photoparty.models.Dao;

import jakarta.servlet.http.HttpServletResponse;
import  mx.edu.utez.photoparty.models.Objects.Cliente;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCliente implements DaoRepository<Cliente> {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cstm;
    private ResultSet rs;


    @Override
    public List<Cliente> findAll(Long id) {
        List<Cliente> clientes = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL all_clientes('')";
            pstm=conn.prepareStatement(query);
            rs= pstm.executeQuery();

            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setId(rs.getString("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setSegnombre(rs.getString("segundo_nombre"));
                cliente.setApe_p(rs.getString("apellido_p"));
                cliente.setApe_m(rs.getString("apellido_m"));
                cliente.setTel(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
                clientes.add(cliente);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return clientes;
    }

    @Override
    public List<Cliente> findAll() {
        return null;
    }



    @Override
    public Cliente findOne(Long id, String id2) {
        try {
            conn = new MySQLConnEmp().connectEmp();
            String query = "CALL all_clientes(?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id2);
            rs = cstm.executeQuery();

            Cliente cliente = new Cliente();
            if(rs.next()){
                cliente.setId(rs.getString("id_cliente"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setSegnombre(rs.getString("segundo_nombre"));
                cliente.setApe_p(rs.getString("apellido_p"));
                cliente.setApe_m(rs.getString("apellido_m"));
                cliente.setTel(rs.getString("telefono"));
                cliente.setCorreo(rs.getString("correo"));
            }
            return cliente;
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    @Override
    public boolean save(Cliente object) {
        try{
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_clientes(?, ?, ?, ?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getId());
            cstm.setString(2, object.getNombre());
            cstm.setString(3, object.getSegnombre());
            cstm.setString(4, object.getApe_p());
            cstm.setString(5, object.getApe_m());
            cstm.setString(6, object.getTel());
            cstm.setString(7, object.getCorreo());
            return cstm.executeUpdate() > 0;
        }catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error al guardar"+e.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Cliente object, String aux) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL actualizar_cliente(?, ?, ?, ?, ?, ?, ?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getId());
            cstm.setString(2, object.getNombre());
            cstm.setString(3, object.getSegnombre());
            cstm.setString(4, object.getApe_p());
            cstm.setString(5, object.getApe_m());
            cstm.setString(6, object.getTel());
            cstm.setString(7, object.getCorreo());
            /*(cliente_id , _nombre , segnombre , apellidop , apellidom , _telefono , _correo )*/
            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoCliente.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
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
            Logger.getLogger(DaoCliente.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }

}
