package mx.edu.utez.photoparty.models.Dao;


import jakarta.servlet.http.HttpServletResponse;
import mx.edu.utez.photoparty.models.Objects.Cabina;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoCabina implements DaoRepository<Cabina> {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cstm;
    private ResultSet rs;
    public void listarImg(int id, HttpServletResponse response) {
        String sql = "CALL all_cabinas("+id+");";

        int bufferSize = 10 * 1024 * 1024; // Tamaño del búfer (10 MB)

        try (Connection conn = new MySQLConnection().connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    InputStream inputStream = rs.getBinaryStream("imagen");
                    response.setContentType("image/*" );

                    try (OutputStream outputStream = response.getOutputStream()) {
                        byte[] buffer = new byte[bufferSize];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
                }
            }
        } catch (Exception e) {
        }
    }

    @Override
    public List<Cabina> findAll(Long id) {
        List<Cabina> cabinas = new ArrayList<>();
        try{
            conn= new MySQLConnection().connect();
            String query = "CALL all_cabinas(0);";
            pstm=conn.prepareStatement(query);
            rs= pstm.executeQuery();

            while(rs.next()){
                Cabina cabina = new Cabina();
                cabina.setId(rs.getLong("id_cabina"));
                cabina.setNombre(rs.getString("nombre"));
                cabina.setDescripcion(rs.getString("descripcion"));
                cabina.setCosto(rs.getFloat("costo"));
                cabina.setExtra(rs.getFloat("precio_hrs_extra"));
                cabina.setActivo(rs.getBoolean("activo"));
                cabina.setImagen(rs.getBinaryStream("imagen"));
                cabinas.add(cabina);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error findAll "+e.getMessage());
        }finally {
            close();
        }
        return cabinas;
    }

    @Override
    public List<Cabina> findAll() {
        return null;
    }

    @Override
    public Cabina findOne(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL all_cabinas(?);";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id);
            rs = pstm.executeQuery();

            Cabina cabina = new Cabina();
            if(rs.next()){
                cabina.setId(rs.getLong("id_cabina"));
                cabina.setNombre(rs.getString("nombre"));
                cabina.setDescripcion(rs.getString("descripcion"));
                cabina.setCosto(rs.getFloat("costo"));
                cabina.setExtra(rs.getFloat("precio_hrs_extra"));
                cabina.setActivo(rs.getBoolean("activo"));
            }
            return cabina;
        }catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName()).log(Level.SEVERE, "Error findOne "+e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    public void imageneslist(int id2, HttpServletResponse response) {

        String sql = "CALL s_imagenes("+id2+");";

        int bufferSize = 10 * 1024 * 1024; // Tamaño del búfer (10 MB)

        try (Connection conn = new MySQLConnection().connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    InputStream inputStream = rs.getBinaryStream("imagen");

                    // Obtener el tipo de imagen (por ejemplo, "jpg", "png", etc.) desde la base de datos

                    // Configurar la respuesta HTTP con el tipo de contenido correcto
                    response.setContentType("image/*" );

                    // Usar BufferedInputStream y BufferedOutputStream para mejorar la eficiencia
                    try (OutputStream outputStream = response.getOutputStream()) {
                        byte[] buffer = new byte[bufferSize];
                        int bytesRead;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                } else {
                    // Manejo de error si no se encuentra la imagen con el ID proporcionado
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Imagen no encontrada");
                }
            }
        } catch (Exception e) {
            // Manejo de excepciones...
        }
    }


    public List<Cabina> imagenes(long id) {
        List<Cabina> cabinas2 = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "SELECT * FROM imagenes WHERE id_cab = ?;";
            pstm = conn.prepareStatement(query);
            pstm.setLong(1, id); // Establecer el ID como parámetro en la consulta
            rs = pstm.executeQuery();

            while (rs.next()) {
                Cabina cabina = new Cabina();
                cabina.setId(rs.getLong("id"));
                cabina.setId_cab(rs.getLong("id_cab"));
                cabina.setNombre(rs.getString("cabina"));
                cabina.setImagen(rs.getBinaryStream("imagen"));
                cabinas2.add(cabina);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error al obtener imágenes por ID: " + e.getMessage());
        } finally {
            close();
        }
        return cabinas2;
    }



    @Override
    public boolean save(Cabina object) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_cabina(?, ?, ?, ?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getNombre());
            cstm.setString(2, object.getDescripcion());
            cstm.setFloat(3, object.getCosto());
            cstm.setFloat(4, object.getExtra());
            cstm.setBlob(5, object.getImagen());

            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public boolean img(Cabina object) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL insertar_img(?, ?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, object.getNombre());
            cstm.setBlob(2, object.getImagen());

            return cstm.executeUpdate() > 0;
        } catch (SQLException e){
            Logger.getLogger(DaoCabina.class.getName()).log(Level.SEVERE, "Error al actualizar" + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean update(Cabina object, String aux) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL actualizar_cabina(?, ?, ?, ?, ?,?);";
            cstm = conn.prepareCall(query);
            cstm.setString(1, aux);
            cstm.setString(2, object.getNombre());
            cstm.setString(3, object.getDescripcion());
            cstm.setFloat(4, object.getCosto());
            cstm.setFloat(5, object.getExtra());
            cstm.setBlob(6, object.getImagen());

            return cstm.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(DaoCabina.class.getName()).log(Level.SEVERE, "Error al actualizar " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean delete(Long id, String id2) {
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL desactivar_cabina(?)";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, id2);
            return pstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }
    public boolean activar(String id){
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL activar_cabina(?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id);
            return cstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
        } finally {
            close();
        }
        return false;
      }

    public boolean eliminar(String id){
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL eliminar_imagen(?)";
            cstm = conn.prepareCall(query);
            cstm.setString(1, id);
            return cstm.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error al eliminar " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }


    public void close(){
        try{
            if(conn != null) conn.close();
            if(pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch(SQLException e){
            Logger.getLogger(DaoCabina.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }
}
