package mx.edu.utez.photoparty.models.Dao;


import mx.edu.utez.photoparty.models.Objects.Evento;
import mx.edu.utez.photoparty.utils.MySQLConnection;
import org.json.simple.parser.ParseException;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoEvento implements DaoRepository<Evento> {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cstm;

    private ResultSet rs;

    public DaoEvento() {

    }

    @Override
    public List<Evento> findAll(Long id) {
        return null;
    }

    @Override
    public List<Evento> findAll() {
        List<Evento> eventos = new ArrayList<>();
        try {
            conn = new MySQLConnection().connect();
            String query = "CALL eventos();";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();

            while (rs.next()) {

                String a = rs.getString("id");
                String nombre = rs.getString("nombre");
                String segnombre = rs.getString("segnombre");
                String ape_p = rs.getString("ape_p");
                String ape_m = rs.getString("ape_m");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String start = rs.getString("start");
                String hora_inicio = rs.getString("hora_inicio");
                String hora_final = rs.getString("hora_final");
                String hora_extra = rs.getString("hora_extra");
                String direccion = rs.getString("direccion");
                String total = rs.getString("total");
                String cabina = rs.getString("cabina");
                String color = rs.getString("color");

                Evento evento = new Evento(a,nombre,segnombre,ape_p,ape_m,telefono,correo,start,hora_inicio,hora_final,hora_extra,direccion,total,cabina,color);

                // Creas un objeto Evento con los datos obtenidos de la base de datos

                eventos.add(evento);
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoEvento.class.getName()).log(Level.SEVERE, "Error al obtener eventos: " + e.getMessage());
        } finally {
            close();
        }
        return eventos;
    }

    @Override
    public Evento findOne(Long id, String id2) {
        return null;
    }


    @Override
    public boolean save(Evento object) {
        return false;
    }

    @Override
    public boolean update(Evento object, String aux) {

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
            Logger.getLogger(DaoEvento.class.getName())
                    .log(Level.SEVERE, "Error closeConnection "+e.getMessage());
        }
    }

}