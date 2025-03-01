package Vistas;

import Modelos.ClientesDAO;
import com.mysql.cj.xdevapi.Table;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ListaClientes extends Stage {
    Scene escena;

    private ToolBar menu;
    private TableView<ClientesDAO> tablaClientes;
    private VBox vBox;
    private Button agregar;
    public ListaClientes()
    {
        CrearUI();
        this.setTitle("Listado de Clientes");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI()
    {
        agregar = new Button();//[pnerle una imagen
        agregar.setGraphic(new ImageView(getClass().getResource("/image/personaIcon.png").toString()));
        tablaClientes = new TableView<>();
        createTable();
        menu = new ToolBar(agregar);
        vBox = new VBox(menu, tablaClientes);
        escena = new Scene(vBox,600,200);
    }

    public void createTable()
    {
        ClientesDAO objCte  = new ClientesDAO();

        TableColumn<ClientesDAO,String> tbName = new TableColumn<>("Nombre");//ahi aparecera el titulo
        tbName.setCellValueFactory(new PropertyValueFactory<>("nomCte"));//callback es una accion que desencadena otra accion automaticamente

        TableColumn<ClientesDAO,String> tbDireccion = new TableColumn<>("Direccion");
        tbDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<ClientesDAO,String> tbTelefono = new TableColumn<>("Telefono");
        tbTelefono.setCellValueFactory(new PropertyValueFactory<>("telefonoCte"));

        TableColumn<ClientesDAO,String> tbEmail = new TableColumn<>("Email");
        tbEmail.setCellValueFactory(new PropertyValueFactory<>("emailCte"));

        tablaClientes.getColumns().addAll(tbName,tbDireccion,tbTelefono,tbEmail);
        tablaClientes.setItems(objCte.SELECT());
    }

}
