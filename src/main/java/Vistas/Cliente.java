package Vistas;

import Modelos.ClientesDAO;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Cliente extends Stage
{
    private Button btnGuarda;
    private Scene escena;
    private TextField txtNomCte, txtDireccion, txtTelefono, txtEmail;
    private VBox vcaja;
    private ClientesDAO objC;
    private TableView<ClientesDAO> tbCliente;

    public Cliente(TableView<ClientesDAO>  tbCte, ClientesDAO obj)
    {
        this.tbCliente =  tbCte;
        CrearUI();
        if(obj == null)
            objC=  new ClientesDAO();
        else
        {
            objC = obj;
            txtNomCte.setText(objC.getNomCte());
            txtTelefono.setText(objC.getTelefonoCte());
            txtDireccion.setText(objC.getDireccion());
            txtEmail.setText(objC.getEmailCte());

        }
            //objC = obj==null? new ClientesDAO() : obj;
        this.setTitle("Registrar cliente");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI()
    {
        txtNomCte = new TextField();
        txtDireccion = new TextField();
        txtTelefono = new TextField();
        txtEmail = new TextField();
        btnGuarda = new Button("Guardar");
        btnGuarda.setOnAction(actionEvent ->
        {
            objC.setNomCte(txtNomCte.getText());
            objC.setDireccion(txtDireccion.getText());
            objC.setTelefonoiCte(txtTelefono.getText());
            objC.setEmailCte(txtEmail.getText());
            if(objC.getIdCte() > 0)
                objC.UPDATE();
            else
                objC.INSERT();
            tbCliente.setItems(objC.SELECT());
            tbCliente.refresh();
            this.close();
        });
        vcaja = new VBox(txtNomCte,txtDireccion,txtTelefono,txtEmail,btnGuarda);
        escena = new Scene(vcaja,120,150);
    }
}