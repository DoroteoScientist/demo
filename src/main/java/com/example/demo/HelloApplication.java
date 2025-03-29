package com.example.demo;

import Modelos.Conexion;
import Vistas.Calculadora;
import Vistas.Celayork;
import Vistas.ListaClientes;
import Vistas.Rompecabezas;
import com.example.demo.Componentes.Hilo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jdk.dynalink.linker.LinkerServices;

import java.io.IOException;
import java.sql.Connection;

public class HelloApplication extends Application {
    //private Button btnSaludar, btnSaludar2, btnSaludar3;
    private VBox vbox;
    private MenuBar mnp;
    private Menu competencia1, competencia2;
    private MenuItem caluladora,restaurante, puzzle,mitHilos;
    private Scene escena;
    private VBox vBox;

    void crearUI()
    {
            caluladora = new MenuItem("Calculadora");
            caluladora.setOnAction(actionEvent -> new Calculadora());

            restaurante = new MenuItem("Clientes");
            restaurante.setOnAction(actionEvent -> new ListaClientes());

            puzzle = new MenuItem("Rompecabezas");
            puzzle.setOnAction(actionEvent -> new Rompecabezas());

            mitHilos = new MenuItem("Carreras (Hilos)");
            mitHilos.setOnAction(actionEvent -> new Celayork());

            competencia1 = new Menu("Compentencia 1");
            competencia1.getItems().addAll(caluladora,restaurante,puzzle);

            competencia2 = new Menu("Compentencia 2");
            competencia2.getItems().addAll(mitHilos);

            mnp = new MenuBar();
            mnp.getMenus().addAll(competencia1,competencia2);



            //Agregar verticalBox al menubar principal
            vBox = new VBox(mnp);
            escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());

    }


    @Override
    public void start(Stage stage) throws IOException {

/*
        new Hilo("ruta Pinos").start();
        new Hilo("ruta Laureles").start();
        new Hilo("ruta San Juan De La Vega").start();
        new Hilo("ruta Monte Blanco").start();
        new Hilo("ruta Teneria").start();


 */
        Conexion.CrearConexion();//manda  a lamar
        crearUI();

        //vbox = new VBox();
        stage.setTitle("Hola Mundo de Eventos :D");
        stage.setScene(escena);
        stage.show();
        stage.setMaximized(true);
    }

    public static void main(String[] args) {launch();}

    void clickEvent(){
        System.out.println("Evento desde un metodo");
    };
}


/*
 btnSaludar = new Button("Binenvenido");
        btnSalud.setOnAction(actionEvent -> {
            System.out.println("Hola Mundo");
        });
        btnSaludar2 = new Button("Binenvenido");
        btnSaludar2.setOnAction(actionEvent ->  clickEvent());
        btnSaludar3 = new Button("Binenvenido");

        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10,0,0,0));

 */