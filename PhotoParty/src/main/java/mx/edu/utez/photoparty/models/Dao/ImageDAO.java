package mx.edu.utez.photoparty.models.Dao;

import mx.edu.utez.photoparty.models.Objects.ImageData;
import mx.edu.utez.photoparty.utils.MySQLConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageDAO  implements DaoRepository<ImageData> {
        private Connection conn;
        private PreparedStatement pstm;
        private CallableStatement cstm;
        private ResultSet rs;

    public boolean insertImages(String cabName, InputStream InputStream ) {

        String sql ="CALL insert_imagenes(?, ?);";
        try (Connection conn = new MySQLConnection().connect();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

                pstm.setString(1, cabName);
                pstm.setBlob(2, InputStream);
                pstm.executeUpdate();


            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
        public List<ImageData> findAll(Long id) {return null;}
        @Override
        public List<ImageData> findAll() {
            return null;
        }
        @Override
        public ImageData findOne(Long id, String id2) {
            return null;
        }

        @Override
        public boolean save(ImageData object) {
            return false;
        }

         @Override
         public boolean update(ImageData object, String aux) {
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
                Logger.getLogger(mx.edu.utez.photoparty.models.Dao.DaoEvento.class.getName())
                        .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
            }
        }



}
