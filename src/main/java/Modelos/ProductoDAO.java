package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

public class ProductoDAO {
    private int cveProducto;
    private String nombProducto;
    private float precio;
    private String descripcion;
    private String imagen;
    private String cveCategoria;

    public int getCveProducto() {
        return cveProducto;
    }

    public void setCveProducto(int cveProducto) {
        this.cveProducto = cveProducto;
    }

    public String getNombProducto() {
        return nombProducto;
    }

    public void setNombProducto(String nombProducto) {
        this.nombProducto = nombProducto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public  String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCveCategoria() {
        return cveCategoria;
    }

    public void setCveCategoria(String cveCategoria) {
        this.cveCategoria = cveCategoria;
    }


    public void INSERT()
    {
        try {

            //MANDAR CONSULTA A LA BD
            String query = "INSERT INTO producto(nombProducto,descripcion,imagen,precio,cveCategoria) " +
                    "values('" + nombProducto + "','" + "','" + descripcion + "','" + Arrays.toString(binarioImagen) + "','" + precio + "','" + cveCategoria + "')";
            try {
                Statement stat = Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
                stat.executeUpdate(query);//todas las operaciones actualaizan la bd
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void UPDATE()
    {
        //MANDAR CONSULTA A LA BD
        String query = "UPDATE producto SET  nombProducto = '"+nombProducto+
                "',descripcion = '"+descripcion+"',imagen = '"+imagen+"',precio = '"+imagen+
                "',cveCategoria = '"+cveCategoria+"' WHERE cveProducto = '"+cveProducto+"'";
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
        String query = "DELETE FROM orden WHERE cveProducto = "+cveProducto;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ProductoDAO> SELECT()
    {
        String query = "SELECT * FROM producto";
        ObservableList<ProductoDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            ProductoDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new ProductoDAO();
                objC.setCveProducto(res.getInt("cveProducto"));
                objC.setNombProducto(res.getString("nombProducto"));
                objC.setPrecio(res.getFloat("precio"));
                objC.setCveCategoria(res.getString("cveCategoria"));
                objC.setDescripcion(res.getString("descripcion"));
                objC.setImagen(res.getString("imagen"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
