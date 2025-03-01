package com.example.demo;

import Modelos.Conexion;
import Vistas.Calculadora;
import Vistas.ListaClientes;
import Vistas.Rompecabezas;
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
    private MenuItem caluladora,restaurante, puzzle;
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

            competencia1 = new Menu("Compentencia 1");
            competencia1.getItems().addAll(caluladora,restaurante,puzzle);
            mnp = new MenuBar();
            mnp.getMenus().addAll(competencia1);

            //Agregar verticalBox al menubar principal
            vBox = new VBox(mnp);
            escena = new Scene(vBox);
        escena.getStylesheets().add(getClass().getResource("/styles/main.css").toString());

    }


    @Override
    public void start(Stage stage) throws IOException {
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