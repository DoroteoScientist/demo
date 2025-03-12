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


    public Rompecabezas() {
        CrearUI();
        this.setTitle("Rompecabezas");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {

        piezas = new GridPane();

        chico = new MenuItem("Piezas chicas");
        chico.setOnAction(actionEvent -> opcionInicio.setText("Piezas chicas"));

        medio = new MenuItem("Piezas medianas");
        medio.setOnAction(actionEvent -> opcionInicio.setText("Piezas medianas"));

        grande = new MenuItem("Piezas grandes");
        grande.setOnAction(actionEvent -> opcionInicio.setText("Piezas grandes"));

        opcionInicio = new TextField("");
        opcionInicio.setEditable(false);
        btnEmpezar = new Button("Empezar");//[pnerle una imagen
        btnEmpezar.setOnAction(actionEvent -> tablero(opcionInicio.getText()));

        opciones = new Menu("Opciones de inicio");
        opciones.getItems().addAll(chico, medio, grande);
        practica3 = new MenuBar();
        practica3.getMenus().addAll(opciones);
        menu = new ToolBar(btnEmpezar, practica3, opcionInicio);
        vBox = new VBox(menu, piezas);
        escena = new Scene(vBox, 600, 200);
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

                    reacomodar(imagenes);

                //reorganizar(imagenes);

                break;
            case "Piezas grandes":
                break;
            case "Piezas medianas":
                break;
        }
    }

    /*
    public void generarAleatorio() {
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                amama = (int) (Math.random() * columna);
                cuadrito[i][j] = amama + 1;
            }

            for (int j = 1; j < columna; j++) {
                if (cuadrito[i][j-1] == cuadrito[i][j])
                {
                    cuadrito[i][j-1] = aleatorio(cuadrito[i][j-1],cuadrito[i][j]);
                }
            }

        }


    }

     */


    public void reorganizar(Label[][] click) {
        // private VBox box new VBox();
        //Label click = new Label();

        for (int i = 0; i < click.length; i++) {
            for (int j = 0; j < click[0].length; j++) {
                int finalJ = j;
                int finalI = i;

                click[i][j].setOnDragDetected(event -> {


                    if (click[finalI][finalJ].getScene() == null) {
                        System.out.println("Intento de arrastre en un Label fuera de la escena.");
                        return; // Evita que se inicie el Drag and Drop si no está en escena.
                    }
                    Dragboard db = click[finalI][finalJ].startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString(finalI + "," + finalJ); // Guardamos la posición original
                    db.setContent(content);
                });

                piezas.setOnDragOver(event -> {
                    if (event.getGestureSource() != piezas && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                });

                click[i][j].setOnDragDropped(event -> {
                    Dragboard db = event.getDragboard();
                    if (db.hasString()) {
                        String[] indices = db.getString().split(",");
                        System.out.println(indices[0] + "" + indices[1]);
                        int oldI = Integer.parseInt(indices[0]);
                        int oldJ = Integer.parseInt(indices[1]);
                        int nuevoJ, nuevoI;
//                        System.out.println(finalI+ " "+finalJ);

                        Label draggedLabel = click[oldI][oldJ];
                        Label destino;


                        Integer newRow = GridPane.getRowIndex(event.getPickResult().getIntersectedNode());
                        Integer newColumn = GridPane.getColumnIndex(event.getPickResult().getIntersectedNode());

                        if (newRow == null) newRow = 0;
                        if (newColumn == null) newColumn = 0;

                        Node targetNode = event.getPickResult().getIntersectedNode();
                        System.out.println("Nodo intersectado: " + targetNode); // Depuración


                        nuevoI = GridPane.getRowIndex(click[finalI][finalJ]);
                        nuevoJ = GridPane.getColumnIndex(click[finalI][finalJ]);

                        //Node targetNode = (Node) event.getGestureTarget();

                        destino = click[newRow][newColumn];

                        System.out.println(nuevoI+""+nuevoJ);
                        //  Removemos el Label de la posición anterior en el GridPane
                        //piezas.getChildren().remove(draggedLabel);

                        if (click[finalI][finalJ].getScene() != null) {
                            db = click[finalI][finalJ].startDragAndDrop(TransferMode.MOVE);
                        } else {
                            System.out.println("Error: Intento de arrastrar un Label que no está en la escena.");
                        }
                        piezas.getChildren().removeAll(draggedLabel, destino);

                        // Intercambiamos en la matriz
                        click[oldI][oldJ] = destino;
                        click[nuevoI][nuevoJ] = draggedLabel;

                        // Agregamos los Labels en la nueva posición
                        piezas.add(draggedLabel, nuevoJ, nuevoI);
                        piezas.add(destino, oldJ, oldI);

                        event.setDropCompleted(true);
                        //  Limpiamos la posición original en la matriz

                        //System.out.println(finalI+ " "+finalJ);
                        //System.out.println(nuevoI+" "+nuevoJ);


                        // Agregamos el Label en la nueva posición


                        // Actualizamos la matriz `click`

                        //System.out.println(db.getString());
                    }

                            event.setDropCompleted(true);
                            event.consume();
                }
                );

            }
        }
    }

    public Label[][] reacomodar(Label[][] click)
    {
        for (int i = 0; i < click.length; i++) {

            for (int j = 0; j < click[0].length; j++) {

                int finalI = i;
                int finalJ = j;

                click[i][j].setOnMousePressed(event -> {
                    // Guardamos la posición inicial
                    click[finalI][finalJ].setUserData(finalI + "," + finalJ);
                });

                click[i][j].setOnMouseReleased(event -> {
                    // Obtenemos la posición de origen
                    String[] indices = ((String) click[finalI][finalJ].getUserData()).split(",");
                    int oldI = Integer.parseInt(indices[0]);
                    int oldJ = Integer.parseInt(indices[1]);

                    // Obtenemos la nueva posición usando el MouseEvent
                    Node targetNode = event.getPickResult().getIntersectedNode();

                    if (targetNode instanceof Label) {
                        Integer newI = GridPane.getRowIndex(targetNode);
                        Integer newJ = GridPane.getColumnIndex(targetNode);

                        if (newI == null) newI = 0;
                        if (newJ == null) newJ = 0;



                        // Intercambiar Labels en la matriz
                        Label draggedLabel = click[oldI][oldJ];
                        Label destino = click[newI][newJ];

                        piezas.getChildren().removeAll(draggedLabel, destino);

                        // Crear nuevos Labels para evitar referencias duplicadas
                        Label newLabel = new Label();
                        newLabel.setGraphic(draggedLabel.getGraphic());  // Copia la imagen del Label original

                        Label newDest = new Label();
                        newDest.setGraphic(destino.getGraphic());  // Copia la imagen del destino

                        agregarEventosDeMovimiento(newLabel, click, newI, newJ);
                        agregarEventosDeMovimiento(newDest, click, oldI, oldJ);


                        click[oldI][oldJ] = newDest;
                        click[newI][newJ] = newLabel;

                        piezas.add(newLabel, newJ, newI);
                        piezas.add(newDest, oldJ, oldI);
                        // Agregar los nuevos Labels en el GridPane

                        imprimirMatriz(click);
                    }
                });
            }
        }

        return imagenes;
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

    public void agregarEventosDeMovimiento(Label label, Label[][] click, int i, int j) {
        label.setOnMousePressed(event -> {
            // Guardamos la posición inicial
            label.setUserData(i + "," + j);
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

                // Intercambiar Labels en la matriz
                Label draggedLabel = click[oldI][oldJ];
                Label destino = click[newI][newJ];

                piezas.getChildren().removeAll(draggedLabel, destino);

                click[oldI][oldJ] = destino;
                click[newI][newJ] = draggedLabel;

                piezas.add(draggedLabel, newJ, newI);
                piezas.add(destino, oldJ, oldI);

                imprimirMatriz(click);  // Para depurar el estado de la matriz después del intercambio
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

}

