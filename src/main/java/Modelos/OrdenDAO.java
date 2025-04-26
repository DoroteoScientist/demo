package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class OrdenDAO {
    private int cveOrden;
    private int cveEmpleado;
    private int cveCliente;
    private int cveMesa;
    private String fecha;
    private String estado;
    private float total;
    private float propina;

    public int getCveOrden() {
        return cveOrden;
    }

    public void setCveOrden(int cveOrden) {
        this.cveOrden = cveOrden;
    }

    public int getCveEmpleado() {
        return cveEmpleado;
    }

    public void setCveEmpleado(int cveEmpleado) {
        this.cveEmpleado = cveEmpleado;
    }

    public int getCveCliente() {
        return cveCliente;
    }

    public void setCveCliente(int cveCliente) {
        this.cveCliente = cveCliente;
    }

    public int getCveMesa() {
        return cveMesa;
    }

    public void setCveMesa(int cveMesa) {
        this.cveMesa = cveMesa;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPropina() {
        return propina;
    }

    public void setPropina(float propina) {
        this.propina = propina;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO orden(cveEmpleado,cveCliente,cveMesa,fecha,estado,total,propina) " +
                "values('"+cveEmpleado+"','"+cveCliente+"','"+cveMesa+"','"+fecha+"','"+estado+"','"+total+"','"+propina+"')";
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
        String query = "UPDATE cliente SET cveCliente = '"+cveCliente+"', cveEmpleado = '"+cveEmpleado+
                "',cveMesa = '"+cveMesa+"',fecha = '"+fecha+"',estado = '"+estado+"',total = '"+estado+
                "',propina = '"+propina+"' WHERE cveOrden = '"+cveOrden+"'";
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
        String query = "DELETE FROM orden WHERE cveOrden = "+cveOrden;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<OrdenDAO> SELECT()
    {
        String query = "SELECT * FROM orden";
        ObservableList<OrdenDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            OrdenDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new OrdenDAO();
                objC.setCveOrden(res.getInt("cveOrden"));
                objC.setCveCliente(res.getInt("cveCliente"));
                objC.setCveEmpleado(res.getInt("cveEmpleado"));
                objC.setCveMesa(res.getInt("cveMesa"));
                objC.setFecha(res.getString("fecha"));
                objC.setEstado(res.getString("estado"));
                objC.setTotal(res.getFloat("total"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
