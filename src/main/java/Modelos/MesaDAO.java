package Modelos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.Statement;

public class MesaDAO {

        private int cveMesa;
        private int numero;
        private int capacidad;
        private String ubicacion;


    public void setCveMesa(int cveMesa) {
        this.cveMesa = cveMesa;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void INSERT()
        {
            //MANDAR CONSULTA A LA BD
            String query = "INSERT INTO mesa(numero,capacidad, ubicacion) " +
                    "values('"+numero+"','"+capacidad+"','"+ubicacion+"')";
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
            String query = "UPDATE mesa SET  numero = '"+numero+"', capacidad = '"+capacidad+
                    "',ubicacion = '"+ubicacion+"' WHERE cveMesa = '"+cveMesa+"'";
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
            String query = "DELETE FROM mesa WHERE cveMesa = "+cveMesa;
            try{
                Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
                stat.executeUpdate(query);//todas las operaciones actualaizan la bd
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public ObservableList<MesaDAO> SELECT()
        {
            String query = "SELECT * FROM mesa";
            ObservableList<MesaDAO> lista = FXCollections.observableArrayList();

            try{
                Statement stat =  Conexion.connection.createStatement(); //crea la consulta. el que lleva lo que va a hacer que arramque
                ResultSet res = stat.executeQuery(query);//todas las operaciones actualaizan la bd
                //apunta a los resultados
                MesaDAO objC;
                while(res.next())//mientras haya ub registro
                {
                    objC = new MesaDAO();
                    objC.setCveMesa(res.getInt("cveMesa"));
                    objC.setNumero(res.getInt("numero"));
                    objC.setCapacidad(res.getInt("capacidad"));
                    objC.setUbicacion(res.getString("ubicacion"));

                    //agarra los valores de res, para que le arroje esos valores

                    lista.add(objC);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return lista;
        }
    }

