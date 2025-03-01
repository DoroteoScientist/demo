package Vistas;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Rompecabezas extends Stage {

    Scene escena;

    private ToolBar menu;
    private VBox vBox;
    private Button btnEmpezar, btnOpcion;
    private GridPane tablerito;
    private MenuBar practica3;
    private MenuItem chico, medio, grande;
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
        //tablero();
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
        Label[][] imagenes;
        Image imagen;
        ImageView imageView;
        switch (opcion) {
            case "Piezas chicas":
                //piezas = new GridPane();
                imagenes = new Label[4][4];
                int numeros[][] = new int[4][4];
                int amama;

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        amama = (int) (Math.random() * 3);
                        numeros[i][j] = amama + 1;
                    }

                    for (int j = 1; j < 4; j++) {
                        if (numeros[i][j - 1] == numeros[i][j]) {
                            numeros[i][j - 1] = aleatorio(numeros[i][j - 1], numeros[i][j]);
                        }
                    }
                }


                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        //imageView = new ImageView(getClass().getResource("/image/puzzle1/row-" + (i + (int) (Math.random() * 3f)) + "-column-" + (j + (int) (Math.random() * 3f)) + ".jpg").toString());
                        imageView = new ImageView(getClass().getResource("/image/puzzle1/row-" + (i + (int) (Math.random() * 3f)) + "-column-" + (j + (int) (Math.random() * 3f)) + ".jpg").toString());
                        imagenes[i][j] = new Label();
                        imagenes[i][j].setGraphic(imageView);
                        piezas.add(imagenes[i][j], j, i);
                    }
                }

                break;
            case "Piezas grandews":
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



    public int aleatorio(int a, int b)
    {
        while ( a == b)
        {
            a = (int) (Math.random() * columna);
            b = (int) (Math.random() * columna);
        }
        return a;
    }
}

