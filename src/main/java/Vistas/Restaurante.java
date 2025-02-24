package Vistas;
/*Ventas_Restaurante*/

import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;
import org.kordamp.bootstrapfx.scene.layout.Panel;

public class Restaurante extends Stage {
    private Panel Panel_restaurante;
    private Scene  escena;
    private Menu menu_comp1;
    private MenuItem item_restaurante,mitCalculadora;
    private MenuBar Principal;
    Restaurante(){
        this.setTitle("Fondita Dona Lupe");
        this.setScene(escena);
        this.show();
    }

    void crearUI(){
        Panel_restaurante= new Panel("Tacos de tripa> ");
        Panel_restaurante.getStyleClass().add("primary panel");
        escena = new Scene(Panel_restaurante,100,200);
        escena.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());

        item_restaurante = new MenuItem("Calculadora");
        item_restaurante.setOnAction(actionEvent -> new Calculadora());
        mitCalculadora = new MenuItem("Restaurante");
        mitCalculadora.setOnAction(actionEvent -> new Restaurante());
        menu_comp1= new Menu("Competencia1");
        menu_comp1.getItems().addAll(mitCalculadora,item_restaurante);
        Principal = new MenuBar();
    }
}
