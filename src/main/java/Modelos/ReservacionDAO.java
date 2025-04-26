package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ReservacionDAO {
    private int cveReservacion;
    private int cveCliente;
    private String fecha;
    private String estado;

    public int getCveReservacion() {
        return cveReservacion;
    }

    public void setCveReservacion(int cveReservacion) {
        this.cveReservacion = cveReservacion;
    }

    public int getCveCliente() {
        return cveCliente;
    }

    public void setCveCliente(int cveCliente) {
        this.cveCliente = cveCliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO reservacion(cveCliente,fecha,estado) " +
                "values('"+cveCliente+"','"+fecha+"','"+estado+"')";
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
        String query = "UPDATE reservacion SET cveCliente = '"+cveCliente+"', fecha = '"+fecha+
                "',estado = '"+estado+"' WHERE cveReservacion = '"+cveReservacion+"'";
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

    public ObservableList<ReservacionDAO> SELECT()
    {
        String query = "SELECT * FROM reservacion";
        ObservableList<ReservacionDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            ReservacionDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ReservacionDAO();
                objC.setCveReservacion(res.getInt("cveReservacion"));
                objC.setCveCliente(res.getInt("cveCliente"));
                objC.setFecha(res.getString("fecha"));
                objC.setEstado(res.getString("estado"));

                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
