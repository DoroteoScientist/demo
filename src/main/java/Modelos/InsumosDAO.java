package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class InsumosDAO {

        private int cveInsumo;
        private String nombre;
        private String unidMedida;
        private int cantidad;
        private int cveProveedor;

    public int getCveInsumo() {
        return cveInsumo;
    }

    public void setCveInsumo(int cveInsumo) {
        this.cveInsumo = cveInsumo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidMedida() {
        return unidMedida;
    }

    public void setUnidMedida(String unidMedida) {
        this.unidMedida = unidMedida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCveProveedor() {
        return cveProveedor;
    }

    public void setCveProveedor(int cveProveedor) {
        this.cveProveedor = cveProveedor;
    }

    public void INSERT()
        {
            //MANDAR CONSULTA A LA BD
            String query = "INSERT INTO cliente(nombre,unidMedida,cantidad,cveProveedor) " +
                    "values('"+nombre+"','"+unidMedida+"','"+cantidad+"','"+cveProveedor+"')";
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
            String query = "UPDATE cliente SET nombre = '"+nombre+
                    "', unidMedida = '"+unidMedida+ "',cantidad = '"+cantidad+"',emailCte = '"+cveProveedor+
                    "' WHERE cveInsumo = '"+cveInsumo+"'";
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
            String query = "DELETE FROM proveedor WHERE cveInsumo = "+cveInsumo;
            try{
                Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
                stat.executeUpdate(query);//todas las operaciones actualaizan la bd
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ObservableList<InsumosDAO> SELECT()
        {
            String query = "SELECT * FROM proveedor";
            ObservableList<InsumosDAO> lista = FXCollections.observableArrayList();

            try{
                Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
                ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
                //apunta a los resultados
                InsumosDAO objC;
                while(res.next())//mientras haya ub registro
                {
                    objC = new InsumosDAO();
                    objC.setCveInsumo(res.getInt("cveInsumo"));
                    objC.setNombre(res.getString("nombre"));
                    objC.setUnidMedida(res.getString("unidMedida"));
                    objC.setCveProveedor(res.getInt("cveProveedor"));
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


