package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriasDAO {
    private int cveCategoria;
    private String nombCategoria;
    private String descripcionCategoria;

    public int getCveCategoria() {
        return cveCategoria;
    }

    public void setCveCategoria(int cveCategoria) {
        this.cveCategoria = cveCategoria;
    }

    public String getNombCategoria() {
        return nombCategoria;
    }

    public void setNombCategoria(String nombCategoria) {
        this.nombCategoria = nombCategoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO categorias(nombCategoria,descripcionCategoria) " +
                "values('"+nombCategoria+"','"+descripcionCategoria+"')";
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
        String query = "UPDATE categorias SET nombCategoria = '"+nombCategoria+
                "', descripcionCategoria = '"+descripcionCategoria+
                "' WHERE cveCategoria = '"+cveCategoria+"'";
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
        String query = "DELETE FROM categorias WHERE cveCategoria = "+cveCategoria;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<CategoriasDAO> SELECT()
    {
        String query = "SELECT * FROM categorias";

        ObservableList<CategoriasDAO> lista = FXCollections.observableArrayList();
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
             CategoriasDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new CategoriasDAO();
                objC.setCveCategoria(res.getInt("cveCategoria"));
                objC.setNombCategoria(res.getString("nombCategoria"));
                objC.setDescripcionCategoria(res.getString("descripcionCategoria"));

                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
