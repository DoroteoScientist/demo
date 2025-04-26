package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class ProveedorDAO {
    private int cveProveedor;
    private String nombProveedor;
    private String telefono;
    private String direccion;
    private String email;
    private String descripcion;

    public int getCveProveedor() {
        return cveProveedor;
    }

    public void setCveProveedor(int cveProveedor) {
        this.cveProveedor = cveProveedor;
    }

    public String getNombProveedor() {
        return nombProveedor;
    }

    public void setNombProveedor(String nombProveedor) {
        this.nombProveedor = nombProveedor;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO cliente(nombProveedor,telefono,direccion,email,descripcion) " +
                "values('"+nombProveedor+"','"+telefono+"','"+direccion+"','"+email+"','"+descripcion+"')";
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
        String query = "UPDATE cliente SET nombProveedor = '"+nombProveedor+", descripcion ="+descripcion+
                "', telefono = '"+telefono+ "',direccion = '"+direccion+"',emailCte = '"+email+
                "' WHERE cveProveedor = '"+cveProveedor+"'";
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
        String query = "DELETE FROM proveedor WHERE cveProveedor = "+cveProveedor;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProveedorDAO> SELECT()
    {
        String query = "SELECT * FROM proveedor";
        ObservableList<ProveedorDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            ProveedorDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ProveedorDAO();
                objC.setCveProveedor(res.getInt("cveProveedor"));
                objC.setNombProveedor(res.getString("nombProveedor"));
                objC.setTelefono(res.getString("telefono"));
                objC.setEmail(res.getString("email"));
                objC.setDireccion(res.getString("direccion"));
                objC.setDescripcion(res.getString("descripcion"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
