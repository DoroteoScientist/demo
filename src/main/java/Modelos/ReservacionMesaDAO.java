package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionMesaDAO {
    private int cveReservacion;
    private int cveMesa;

    public int getCveReservacion() {
        return cveReservacion;
    }

    public void setCveReservacion(int cveReservacion) {
        this.cveReservacion = cveReservacion;
    }

    public int getCveMesa() {
        return cveMesa;
    }

    public void setCveMesa(int cveMesa) {
        this.cveMesa = cveMesa;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO reservacionMesa (cveReservacion, cveMesa) " +
                "values('"+cveReservacion+"','"+cveMesa+"')";
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE()
    {
        //MANDAR CONSULTA A LA BD
        String query = "UPDATE reservacionMesa SET cveReservacion = '"+cveReservacion+"', " +
                "+cveMesa = '"+cveMesa+"'"+"' WHERE cveReservacion = '"+cveReservacion+"'";
        //asi sin nada, se actualizarian todos los registros con un solo dato, asi
        //que hay que ponerle el WHERE
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void DELETE()
    {
        String query = "DELETE FROM reservacion WHERE cveReservacion = "+cveReservacion;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ReservacionMesaDAO> SELECT()
    {
        String query = "SELECT * FROM reservacionMesa";
        ObservableList<ReservacionMesaDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            ReservacionMesaDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ReservacionMesaDAO();
                objC.setCveReservacion(res.getInt("cveReservacion"));
                objC.setCveMesa(res.getInt("cveMesa"));
                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
