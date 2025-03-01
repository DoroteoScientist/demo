package Vistas;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;

public class Cliente extends Stage
{
    private Button btnGuarda;
    private Scene escena;
    private TextField txtNomCte;
    private TextField txtDireccion;
    private TextField txtTelefono;
    private TextField txtEmail;
    private VBox vcaja;

    public Cliente()
    {
        CrearUI();
        this.setTitle("Registrar cliente");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI()
    {
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelefono = new TextField();
        txtEmail = new TextField();
        btnGuarda = new Button("Guardar");
        vcaja = new VBox();

        escena = new Scene(vcaja,120,150);
    }
}