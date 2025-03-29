package Vistas;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;

public class Rompecabezas extends Stage {

    Scene escena;

    private ToolBar menu;
    private VBox vBox;
    private Button btnEmpezar, btnOpcion;
    private GridPane tablerito;
    private MenuBar practica3;
    private MenuItem chico, medio, grande;
    private Label[][] imagenes;
    private Menu opciones;
    private TextField opcionInicio;
    private GridPane piezas;
    private long tiempoInicion,tiempoFinal;
    private RandomAccessFile regTiempos;

    public Rompecabezas() {
        CrearUI();
        this.setTitle("Rompecabezas");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {

        piezas = new GridPane();

        chico = new MenuItem("Piezas chicas");
        chico.setOnAction(actionEvent ->
            opcionInicio.setText("Piezas chicas"));

        medio = new MenuItem("Piezas medianas");
        medio.setOnAction(actionEvent ->
        opcionInicio.setText("Piezas medianas"));

        grande = new MenuItem("Piezas grandes");
        grande.setOnAction(actionEvent -> opcionInicio.setText("Piezas grandes"));

        opcionInicio = new TextField("");
        opcionInicio.setEditable(false);
        btnEmpezar = new Button("Empezar");//[pnerle una imagen
        btnEmpezar.setOnAction(actionEvent ->
        {
            tiempoInicion = System.currentTimeMillis();
            tablero(opcionInicio.getText());
        });

        opciones = new Menu("Opciones de inicio");
        opciones.getItems().addAll(chico, medio, grande);
        practica3 = new MenuBar();
        practica3.getMenus().addAll(opciones);
        menu = new ToolBar(btnEmpezar, practica3, opcionInicio);
        vBox = new VBox(menu, piezas);
        escena = new Scene(vBox, 600, 200);
    }

    public void ventanaTiempo(long tiempoUno, long tiempoDos)
    {
        Stage ventana;
        Scene escena;

    }

    public void tablero(String opcion) {
        Label[][] imagenesOriginal;
        Image imagen;
        ImageView imageView;
        switch (opcion) {
            case "Piezas chicas":
                //piezas = new GridPane();
                imagenes = new Label[4][4];
                imagenesOriginal = new Label[4][4];

                String numeros[][] = new String[4][4];
                //int numeros2[][] = new int[4][4];
                int amama;

                numeros = generarCuadroCoordenadas(4,4,numeros);
                //numeros2 = generarCuadroNoRepetido(4,4,numeros2);

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        //imageView = new ImageView(getClass().getResource("/image/puzzle1/row-" + (i + (int) (Math.random() * 3f)) + "-column-" + (j + (int) (Math.random() * 3f)) + ".jpg").toString());
                        imageView = new ImageView(getClass().getResource("/image/puzzle1/row-"
                                + (Integer.parseInt(numeros[i][j].substring(0,1))+1) + "-column-"
                                + (Integer.parseInt(numeros[i][j].substring(2))+1) + ".jpg").toString());
                        imagenes[i][j] = new Label();
                        imagenes[i][j].setGraphic(imageView);
                        piezas.add(imagenes[i][j], j, i);
                    }
                }

                for (int i = 0; i < imagenes.length; i++) {
                    for (int j = 0; j < imagenes[0].length; j++) {
                        imageView = new ImageView(getClass().getResource("/image/puzzle1/row-"
                                + (i+1) + "-column-"
                                + (j+1) + ".jpg").toString());
                        imagenesOriginal[i][j] = new Label();
                        imagenesOriginal[i][j].setGraphic(imageView);
                        //piezas.add(imagenes[i][j], j, i);
                    }
                }

                do {
                    reacomodar(imagenes);
                }while (imagenes != imagenesOriginal);
                tiempoFinal = System.currentTimeMillis()-tiempoInicion;
                registrarTiempos(tiempoFinal);
                break;
            case "Piezas grandes":
                break;
            case "Piezas medianas":
                break;
        }
    }


    private void registrarTiempos(long tiempo)
    {
        try{
            regTiempos = new RandomAccessFile("\\..\\resources\\docuemnts\\tiempoos","rw");
            regTiempos.writeLong(tiempo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void imprimirMatriz(Label[][] click) {
        System.out.println("Estado actual de la matriz:");
        for (int i = 0; i < click.length; i++) {
            for (int j = 0; j < click[i].length; j++) {
                // Muestra la referencia del objeto en memoria si el Label existe
                System.out.print("[ " + (click[i][j] != null ? click[i][j].hashCode() : "null") + " ] ");
            }
            System.out.println();
        }
        System.out.println("---------------------");
    }

    public void agregarEventosDeMovimiento(Label label, Label[][] click, int originalI, int originalJ) {
        label.setOnMousePressed(event -> {
            // Guardamos la posición inicial
            label.setUserData(originalI + "," + originalJ);
        });

        label.setOnMouseReleased(event -> {
            // Obtenemos la posición de origen
            String[] indices = ((String) label.getUserData()).split(",");
            int oldI = Integer.parseInt(indices[0]);
            int oldJ = Integer.parseInt(indices[1]);

            // Obtenemos la nueva posición usando el MouseEvent
            Node targetNode = event.getPickResult().getIntersectedNode();

            if (targetNode instanceof Label) {
                Integer newI = GridPane.getRowIndex(targetNode);
                Integer newJ = GridPane.getColumnIndex(targetNode);

                if (newI == null) newI = 0;
                if (newJ == null) newJ = 0;

                // Verificamos que no estemos intentando mover a la misma posición
                if (oldI == newI && oldJ == newJ) {
                    return;
                }

                // Intercambiar Labels en la matriz
                Label draggedLabel = click[oldI][oldJ];
                Label destino = click[newI][newJ];

                // Removemos los Labels antiguos
                piezas.getChildren().remove(draggedLabel);
                piezas.getChildren().remove(destino);

                // Creamos nuevos Labels para evitar referencias duplicadas
                Label newDraggedLabel = new Label();
                newDraggedLabel.setGraphic(draggedLabel.getGraphic());

                Label newDestinoLabel = new Label();
                newDestinoLabel.setGraphic(destino.getGraphic());

                // Actualizamos la matriz con las nuevas referencias
                click[oldI][oldJ] = newDestinoLabel;
                click[newI][newJ] = newDraggedLabel;

                // Agregamos eventos a los nuevos Labels
                agregarEventosDeMovimiento(newDraggedLabel, click, newI, newJ);
                agregarEventosDeMovimiento(newDestinoLabel, click, oldI, oldJ);

                // Agregamos los nuevos Labels al GridPane
                piezas.add(newDraggedLabel, newJ, newI);
                piezas.add(newDestinoLabel, oldJ, oldI);

                imprimirMatriz(click);
            }
        });
    }
    public String[][] generarCuadroCoordenadas(int fila, int columna, String[][] cuadrito) {
        for (int i = 0; i < fila; i++) {
            ArrayList<String> coordenadas = new ArrayList<>();

            // Llenamos la lista con las coordenadas de la fila actual
            for (int j = 0; j < columna; j++) {
                coordenadas.add(i+","+j);
            }

            // Mezclamos aleatoriamente la lista
            Collections.shuffle(coordenadas);

            // Llenamos la matriz con las coordenadas desordenadas
            for (int j = 0; j < columna; j++) {
                cuadrito[i][j] = coordenadas.get(j);
            }
        }
        return cuadrito;
    }

    public Label[][] reacomodar(Label[][] click) {
        for (int i = 0; i < click.length; i++) {
            for (int j = 0; j < click[0].length; j++) {
                int finalI = i;
                int finalJ = j;

                agregarEventosDeMovimiento(click[i][j], click, finalI, finalJ);
            }
        }
        return click;
    }

}

