package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class PagoDAO {
    private int cvePago;
    private int cveOrden;
    private float monto;
    private String formaPago;
    private String fechaPago;
    private String observaciones;

    public int getCvePago() {
        return cvePago;
    }

    public void setCvePago(int cvePago) {
        this.cvePago = cvePago;
    }

    public int getCveOrden() {
        return cveOrden;
    }

    public void setCveOrden(int cveOrden) {
        this.cveOrden = cveOrden;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO pago(cveOrden,formaPago,fechaPago,monto,observaciones) " +
                "values('"+cveOrden+"','"+"','"+formaPago+"','"+fechaPago+"','"+monto+"','"+observaciones+"')";
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
        String query = "UPDATE pago SET  cveOrden = '"+cveOrden+
                "',formaPago = '"+formaPago+"',fechaPago = '"+fechaPago+"',monto = '"+fechaPago+
                "',observaciones = '"+observaciones+"' WHERE cvePago = '"+cvePago+"'";
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
        String query = "DELETE FROM orden WHERE cvePago = "+cvePago;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<PagoDAO> SELECT()
    {
        String query = "SELECT * FROM pago";
        ObservableList<PagoDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            PagoDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new PagoDAO();
                objC.setCveOrden(res.getInt("cvePago"));
                objC.setCveOrden(res.getInt("cveOrden"));
                objC.setMonto(res.getFloat("monto"));
                objC.setObservaciones(res.getString("observaciones"));
                objC.setFormaPago(res.getString("formaPago"));
                objC.setFechaPago(res.getString("fechaPago"));


                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
