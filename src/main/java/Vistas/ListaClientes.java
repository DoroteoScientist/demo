package Vistas;

import Modelos.ClientesDAO;
import com.example.demo.Componentes.ButtonCell;
import com.mysql.cj.xdevapi.Table;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ListaClientes extends Stage {
    Scene escena;

    private ToolBar menu;
    private TableView<ClientesDAO> tablaClientes;
    private VBox vBox;
    private Button agregar;
    private ClientesDAO objC;

    public ListaClientes()
    {
        objC = new ClientesDAO();
        CrearUI();
        this.setTitle("Listado de Clientes");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI()
    {
        agregar = new Button();//[pnerle una imagen
        agregar.setOnAction(actionEvent -> new Cliente(tablaClientes,null));
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

        TableColumn<ClientesDAO,String> tbcEditar = new TableColumn<>("Editar");
        TableColumn<ClientesDAO,String> tbcEliminar = new TableColumn<>("Eliminar");
        tbcEditar.setCellFactory(new Callback<TableColumn<ClientesDAO, String>, TableCell<ClientesDAO, String>>() {
            @Override
            public TableCell<ClientesDAO, String> call(TableColumn<ClientesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell("Editar");
            }
        });

        tbcEliminar.setCellFactory(new Callback<TableColumn<ClientesDAO, String>, TableCell<ClientesDAO, String>>() {
            @Override
            public TableCell<ClientesDAO, String> call(TableColumn<ClientesDAO, String> clientesDAOStringTableColumn) {
                return new ButtonCell("Eliminar");
            }
        });

        tablaClientes.getColumns().addAll(tbName,tbDireccion,tbTelefono,tbEmail,tbcEditar,tbcEliminar);
        tablaClientes.setItems(objCte.SELECT());
    }

}
