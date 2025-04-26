package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class DetOrdenDAO {

    private int cveOrden;
    private int cveProducto;
    private int cantidad;

    public int getCveOrden() {
        return cveOrden;
    }

    public void setCveOrden(int cveOrden) {
        this.cveOrden = cveOrden;
    }

    public int getCveProducto() {
        return cveProducto;
    }

    public void setCveProducto(int cveProducto) {
        this.cveProducto = cveProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO DetOrden(cveProducto,cantidad) " +
                "values('"+cveProducto+"','"+cantidad+"')";
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
        String query = "UPDATE DetOrde SET  cveProducto = '"+cveProducto+"', cantidad = '"+cantidad+
                "' WHERE cveOrden = '"+cveOrden+"'";
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
        String query = "DELETE FROM DetOrden WHERE cveOrden = "+cveOrden;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<DetOrdenDAO> SELECT()
    {
        String query = "SELECT * FROM DetOrden";
        ObservableList<DetOrdenDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            DetOrdenDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new DetOrdenDAO();
                objC.setCveOrden(res.getInt("cveOrden"));
                objC.setCveProducto(res.getInt("cveProducto"));
                objC.setCantidad(res.getInt("cantidad"));

                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
