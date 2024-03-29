package mx.edu.utez.photoparty.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnEmp {
    final String DBNAME = "integradora",
            USER = "empleado", PASSWORD = "Photoparty_empleado01",
            TIMEZONE = "America/Mexico_City",
            USESSL = "false", PUBLICKEY = "true",
            DDLAUTO = "true", HOST = "52.86.87.229";

    public Connection connectEmp(){
        String dataSource = String.format(
                "jdbc:mysql://%s:3306/%s?user=%s&password=%s&serverTimezone=%s&useSSL=%s&allowPublicKeyRetrieval=%s&createDatabaseIfNotExist=%s",
                HOST, DBNAME, USER, PASSWORD, TIMEZONE, USESSL, PUBLICKEY, DDLAUTO);
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            return DriverManager.getConnection(dataSource);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            Connection conn = new MySQLConnEmp().connectEmp();
            if (conn != null) {
                System.out.println("Conexión exitosa");
                conn.close();
            }else {
                System.out.println("Conexión fallida");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
