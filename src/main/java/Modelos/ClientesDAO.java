package Modelos;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ClientesDAO {

    private int cveCliente;
    private String nombCliente;
    private String apePatCliente;
    private String apeMatCliente;
    private String telCliente;
    private String direccion;
    private String emailCliente;

    public int getCveCliente() {return cveCliente;}

    public void setCveCliente(int idCliente) {this.cveCliente = idCliente;}

    public String getNombCliente() {return nombCliente;}

    public void setNombCliente(String nombCliente) {this.nombCliente = nombCliente;}

    public String getApePatEmpleado() {return apePatCliente;}

    public void setApePatEmpleado(String apePatEmpleado) {this.apePatCliente = apePatEmpleado;}

    public String getApeMatEmpleado() {return apeMatCliente;}

    public void setApeMatEmpleado(String apeMatEmpleado) {this.apeMatCliente = apeMatEmpleado;}

    public String getTelCliente() {return telCliente;}

    public void setTelCliente(String telCliente) {this.telCliente = telCliente;}

    public String getDireccion() {return direccion;}

    public void setDireccion(String direccion) {this.direccion = direccion;}

    public String getEmailCliente() {return emailCliente;}

    public void setEmailCliente(String emailCliente) {this.emailCliente = emailCliente;}

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO cliente(nombCliente,apePatCliente,apeMatCliente,telCliente,direccion,emailCliente) " +
                "values('"+nombCliente+"','"+apePatCliente+"','"+apeMatCliente+"','"+telCliente+"','"+direccion+"','"+emailCliente+"')";
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
        String query = "UPDATE cliente SET nombCliente = '"+nombCliente+"apePatCliente"+apePatCliente+"apeMatCliente"+apeMatCliente+
                "', telCliente = '"+telCliente+ "',direccion = '"+direccion+"',emailCte = '"+emailCliente+
                "' WHERE idCte = '"+cveCliente+"'";
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
        String query = "DELETE FROM cliente WHERE idCte = "+cveCliente;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ClientesDAO> SELECT()
    {
        String query = "SELECT * FROM clientes";
        ObservableList<ClientesDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
        //apunta a los resultados
            ClientesDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ClientesDAO();
                objC.setCveCliente(res.getInt("cveCliente"));
                objC.setNombCliente(res.getString("nombCliente"));
                objC.setApePatEmpleado(res.getString("apePatCliente"));
                objC.setApeMatEmpleado(res.getString("apeMatCliente"));
                objC.setTelCliente(res.getString("telCliente"));
                objC.setDireccion(res.getString("direccion"));
                objC.setEmailCliente(res.getString("emailCliente"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
