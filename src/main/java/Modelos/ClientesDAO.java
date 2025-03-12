package Modelos;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientesDAO {

    private int idCte;
    private String nomCte;
    private String telefonoiCte;
    private String direccion;
    private String emailCte;

    public String getNomCte() {
        return nomCte;
    }

    public void setNomCte(String nomCte) {
        this.nomCte = nomCte;
    }

    public String getTelefonoCte() {
        return telefonoiCte;
    }

    public void setTelefonoiCte(String telefonoiCte) {
        this.telefonoiCte = telefonoiCte;
    }

    public String getEmailCte() {
        return emailCte;
    }

    public void setEmailCte(String emailCte) {
        this.emailCte = emailCte;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdCte() {
        return idCte;
    }

    public void setIdCte(int idCte) {
        this.idCte = idCte;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO cliente(nomCte,telefonoCte,direccion,emailCte) " +
                "values('"+nomCte+"','"+telefonoiCte+"','"+direccion+"','"+emailCte+"')";
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
        String query = "UPDATE cliente SET nomCte = '"+nomCte+"', telefonoCte = '"+telefonoiCte+
                "',direccion = '"+direccion+"',emailCte = '"+emailCte+"' WHERE idCte = '"+idCte+"'";
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
        String query = "DELETE FROM cliente WHERE idCte = "+idCte;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClientesDAO> SELECT()
    {
        String query = "SELECT * FROM cliente";
        ObservableList<ClientesDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
        //apunta a los resultados
            ClientesDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ClientesDAO();
                objC.setIdCte(res.getInt("idCte"));
                objC.setNomCte(res.getString("nomCte"));
                objC.setTelefonoiCte(res.getString("telefonoCte"));
                objC.setDireccion(res.getString("direccion"));
                objC.setEmailCte(res.getString("emailCte"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
