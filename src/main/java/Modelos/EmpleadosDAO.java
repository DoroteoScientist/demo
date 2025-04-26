package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadosDAO {

    private int cveEmpleado;
    private String nombEmpleado;
    private String apePatEmpleado;
    private String apeMatEmpleado;
    private String CURP;
    private String RFC;
    private String NSS;
    private String sueldo;
    private String puesto;
    private String noContacto;
    private String fechaIngreso;
    private String horario;
    private boolean activo;

    public int getCveEmpleado() {
        return cveEmpleado;
    }

    public void setCveEmpleado(int cveEmpleado) {
        this.cveEmpleado = cveEmpleado;
    }

    public String getNombEmpleado() {
        return nombEmpleado;
    }

    public void setNombEmpleado(String nombEmpleado) {
        this.nombEmpleado = nombEmpleado;
    }

    public String getApePatEmpleado() {
        return apePatEmpleado;
    }

    public void setApePatEmpleado(String apePatEmpleado) {
        this.apePatEmpleado = apePatEmpleado;
    }

    public String getApeMatEmpleado() {
        return apeMatEmpleado;
    }

    public void setApeMatEmpleado(String apeMatEmpleado) {
        this.apeMatEmpleado = apeMatEmpleado;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getRFC() {
        return RFC;
    }

    public void setRFC(String RFC) {
        this.RFC = RFC;
    }

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNoContacto() {
        return noContacto;
    }

    public void setNoContacto(String noContacto) {
        this.noContacto = noContacto;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }


    public void INSERT()
    {
        //MANDAR CONSULTA A LA BD
        String query = "INSERT INTO empleados(cveEmpleado,nombEmpleado,apePatEmpleado,apeMatEmpleado,CURP,RFC,NSS,sueldo,puesto,noContacto,fechaIngreso,horario,activo) " +
                "values('"+cveEmpleado+"','"+nombEmpleado+"','"+apePatEmpleado+"','"+apeMatEmpleado+"','"+CURP+"','"+
                RFC+"','"+NSS+"','"+sueldo+"','"+puesto+"','"+noContacto+"','"+fechaIngreso+"','"+horario+"','"+activo+"')";
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
        String query = "UPDATE empleados SET nombCliente = '"+nombEmpleado+"', apePatEmpleado = '"+apePatEmpleado+"', apeMatEmpleado = '"+apeMatEmpleado+
                "', CURP = '"+CURP+"', RFC = '"+RFC+"', NSS = '"+NSS+"', sueldo = '"+sueldo+"', puesto = '"+puesto+"', noContacto ='"+ noContacto+
                "', fechaIngreso =  '"+fechaIngreso+"', horario = '"+horario+"', activo = '"+activo+'"';
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
        String query = "DELETE FROM cliente WHERE cveEmpleado = '"+cveEmpleado;
        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            stat.executeUpdate(query);//todas las operaciones actualaizan la bd
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<EmpleadosDAO> SELECT()
    {
        String query = "SELECT * FROM empleados";
        ObservableList<EmpleadosDAO> lista = FXCollections.observableArrayList();

        try{
            Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
            ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
            //apunta a los resultados
            EmpleadosDAO objC;
            while(res.next())//mientras haya ub registro
            {
                objC = new EmpleadosDAO();
                objC.setCveEmpleado(res.getInt("cveEmpleado"));
                objC.setNombEmpleado(res.getString("nombEmpleado"));
                objC.setApePatEmpleado(res.getString("apePatEmpleado"));
                objC.setApeMatEmpleado(res.getString("apeMatEmpleado"));
                objC.setCURP(res.getString("CURP"));
                objC.setRFC(res.getString("RFC"));
                objC.setNSS(res.getString("NSS"));
                objC.setSueldo(res.getString("sueldo"));
                objC.setPuesto(res.getString("puesto"));
                objC.setNoContacto(res.getString("noContacto"));
                objC.setFechaIngreso(res.getString("fechaIngreso"));
                objC.setHorario(res.getString("horario"));
                objC.setActivo(res.getBoolean("activo"));



                //agarra los valores de res, para que le arroje esos valores

                lista.add(objC);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
