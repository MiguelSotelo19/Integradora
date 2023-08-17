package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.Usuario;
import mx.edu.utez.photoparty.utils.MySQLConnection;
import mx.edu.utez.photoparty.utils.MySQLConnEmp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUsuario implements DaoRepository<Usuario> {
    private Connection conn;
    private CallableStatement cstm;
    private ResultSet rs;
    private String key = "tgyhu3jkdn54i3ls8a4";


    @Override
    public List<Usuario> findAll(Long id) {
        List<Usuario> usuarios = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL all_users('', ?);";
            cstm=conn.prepareCall(query);
            cstm.setString(1, key);
            rs= cstm.executeQuery();

            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setSegnombre(rs.getString("segundo_nombre"));
                usuario.setApe_p(rs.getString("apellido_p"));
                usuario.setApe_m(rs.getString("apellido_m"));
                usuario.setTel(rs.getString("telefono"));
                usuario.setUsuario(rs.getString("usuario"));
                usuarios.add(usuario);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return usuarios;
    }

    @Override
    public List<Usuario> findAll() {
        return null;
    }

    @Override
    public Usuario findOne(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL all_users(?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setLong(1, id);
            cstm.setString(2, key);
            rs = cstm.executeQuery();

            Usuario usuario = new Usuario();
            if(rs.next()){
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setSegnombre(rs.getString("segundo_nombre"));
                usuario.setApe_p(rs.getString("apellido_p"));
                usuario.setApe_m(rs.getString("apellido_m"));
                usuario.setTel(rs.getString("telefono"));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContrasenia(rs.getString("contrasenia"));
            }
            return usuario;
        }catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
            return null;
        } finally {
            close();
        }
    }

    @Override
    public boolean save(Usuario object) {
        try{
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_usuario(?, ?, ?, ?, ?, ?, ?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getNombre());
            cstm.setString(2, object.getSegnombre());
            cstm.setString(3, object.getApe_p());
            cstm.setString(4, object.getApe_m());
            cstm.setString(5, object.getTel());
            cstm.setString(6, object.getUsuario());
            cstm.setString(7, object.getContrasenia());
            cstm.setString(8, key);
            return cstm.executeUpdate() > 0;
        }catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName())
                    .log(Level.SEVERE, "Error al guardar"+e.getMessage());
            return false;
        }finally {
            close();
        }
    }

    @Override
    public boolean update(Usuario object, String aux) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL actualizar_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setLong(1, object.getId());
            cstm.setString(2, object.getNombre());
            cstm.setString(3, object.getSegnombre());
            cstm.setString(4, object.getApe_p());
            cstm.setString(5, object.getApe_m());
            cstm.setString(6, object.getTel());
            cstm.setString(7, object.getUsuario());
            cstm.setString(8, object.getContrasenia());
            cstm.setString(9, key);
            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    @Override
    public boolean delete(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL eliminar_usuario(?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id2);
            return cstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoContrato.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
            return false;
        } finally {
            close();
        }
    }

    public Usuario validate(String user, String pass){
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL validar_usuario(?, ?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, user);
            cstm.setString(2, pass);
            cstm.setString(3, key);
            rs= cstm.executeQuery();
            if(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setUsuario(rs.getString("usuario"));
                return usuario;
            }
        } catch (SQLException e){
            Logger.getLogger(DaoContrato.class.getName())
                    .log(Level.SEVERE, "Error al consultar " + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    public List<Usuario> v_users(String id) {
        List<Usuario> usuarios = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL v_usuarios(?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id);
            rs= cstm.executeQuery();

            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setId(rs.getLong("id_usuario"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setSegnombre(rs.getString("segundo_nombre"));
                usuario.setApe_p(rs.getString("apellido_p"));
                usuario.setApe_m(rs.getString("apellido_m"));
                usuario.setTel(rs.getString("telefono"));
                usuario.setUsuario(rs.getString("usuario"));
                usuarios.add(usuario);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoUsuario.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return usuarios;
    }

    public void close(){
        try{
            if(conn != null) conn.close();
            if(cstm != null) cstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoUsuario.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }

}
