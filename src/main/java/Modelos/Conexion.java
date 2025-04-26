package Modelos;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurantedb";
    static private String USER = "admin3";
    static private String PWD = "1234";
    static private String HOST = "localhost";
    static private String PORT =  "3306";
    public static Connection connection;//apunta a la conexion a la BD

    public static void CrearConexion()
    {
        //Es necesario siempre poner un try catch a la hora de manejar recursos en Java
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");//generar conexion
                connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD) ;
            //socket es un mecanismo de comunicacion para intercambiar datos de 2 diferentes apps
            System.out.println("Conexion establecida :D");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
