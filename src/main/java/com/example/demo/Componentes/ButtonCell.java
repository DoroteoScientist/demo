package com.example.demo.Componentes;

import Modelos.ClientesDAO;
import Vistas.Cliente;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;

import java.beans.EventHandler;
import java.util.Optional;

//esto tiene que tener un boton
//esto es para actualziar elemenetos
public class ButtonCell extends TableCell<ClientesDAO,String>
{
    Button btnCelda;
    private String strButton;

    public ButtonCell(String label)
    {
        //Recupera de un tableView sus items, y de ese item recuperar su indice
        //el this aqui hara referencia a la tabla donde esta instanciado

        strButton = label;
        btnCelda = new Button(strButton);
        btnCelda.setOnAction(actionEvent -> {
        ClientesDAO objC =  this.getTableView().getItems().get(this.getIndex());
            if(strButton.equals("Editar"))
            {
                 new Cliente(this.getTableView(),objC);
            }
            else
            {
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmacion");
                alerta.setContentText("Desea eliminar este registro?");
                Optional<ButtonType> opcion = alerta.showAndWait();
                if(opcion.get() == ButtonType.OK)
                    objC.DELETE();
            }
            this.getTableView().setItems(objC.SELECT());
            this.getTableView().refresh();
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if(!empty)
            this.setGraphic(btnCelda);//repintar el boton
    }
}
