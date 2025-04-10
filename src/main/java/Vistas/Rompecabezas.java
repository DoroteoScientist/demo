package Vistas;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class Rompecabezas extends Stage {

    Scene escena;

    private ToolBar menu;
    private VBox vBox;
    private Button btnEmpezar;
    private MenuBar practica3;
    private MenuItem chico, medio, grande;
    private Label[][] imagenes;
    private Menu opciones;
    private TextField opcionInicio;
    private GridPane piezas;
    private GridPane piezasOriginales; // GridPane para mantener el orden correcto
    private long tiempoInicion,tiempoFinal;
    private TableView<String> intento;
    private Label tiempo;
    private  Timeline cuentador;

    public Rompecabezas() {
        CrearUI();
        this.setTitle("Rompecabezas");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {

        piezas = new GridPane();
        piezasOriginales = new GridPane(); // Inicializa el GridPane de piezas originales

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
        tiempo = new Label("0");
        menu = new ToolBar(btnEmpezar, practica3, opcionInicio,tiempo);
        vBox = new VBox(menu, piezas);
        escena = new Scene(vBox, 600, 600); // Aumentamos el tamaño para que quepa el puzzle
    }

    public void tablero(String opcion) {
        piezas.getChildren().clear();
        piezasOriginales.getChildren().clear();
        String[][] coordenadasMezcladas;

        switch (opcion) {
            case "Piezas chicas":

                contador();
                imagenes = new Label[4][4];

                coordenadasMezcladas = new String[4][4];
                coordenadasMezcladas = generarCuadroCoordenadas(4, 4, coordenadasMezcladas);

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        String[] coords = coordenadasMezcladas[i][j].split(",");
                        int rowCoord = Integer.parseInt(coords[0]);
                        int colCoord = Integer.parseInt(coords[1]);

                        String imagePath = "/image/puzzle1/row-" + (rowCoord + 1) + "-column-" + (colCoord + 1) + ".jpg";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);


                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 1px;");
                        // Almacenar la posición correcta como metadatos
                        label.setUserData(rowCoord + "," + colCoord);

                        // Configurar el drag and drop para cada pieza
                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }

                break;

            case "Piezas medianas":

                contador();
                imagenes = new Label[5][5];

                coordenadasMezcladas = new String[5][5];
                coordenadasMezcladas = generarCuadroCoordenadas(5, 5, coordenadasMezcladas);

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        String[] coords = coordenadasMezcladas[i][j].split(",");
                        int rowCoord = Integer.parseInt(coords[0]);
                        int colCoord = Integer.parseInt(coords[1]);

                        String imagePath = "/image/puzzle2/row-" + (rowCoord + 1) + "-column-" + (colCoord + 1) + ".png";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);

                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setStyle("-fx-border-color: purple; -fx-border-width: 1px; -fx-padding: 1px;");
                        // Almacenar la posición correcta como metadatos
                        label.setUserData(rowCoord + "," + colCoord);

                        // Configurar el drag and drop para cada pieza
                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }

                break;

            case "Piezas grandes":

                contador();
                imagenes = new Label[6][6];

                coordenadasMezcladas = new String[6][6];
                coordenadasMezcladas = generarCuadroCoordenadas(6, 6, coordenadasMezcladas);

                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        String[] coords = coordenadasMezcladas[i][j].split(",");
                        int rowCoord = Integer.parseInt(coords[0]);
                        int colCoord = Integer.parseInt(coords[1]);

                        String imagePath = "/image/puzzle3/row-" + (rowCoord + 1) + "-column-" + (colCoord + 1) + ".png";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);

                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setStyle("-fx-border-color: red; -fx-border-width: 3px; -fx-padding: 1px;");
                        // Almacenar la posición correcta como metadatos
                        label.setUserData(rowCoord + "," + colCoord);

                        // Configurar el drag and drop para cada pieza
                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }

                break;

            default:
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Selección requerida");
                alerta.setHeaderText("Selecciona un tamaño de piezas");
                alerta.setContentText("Por favor, selecciona un tamaño de piezas antes de empezar.");
                alerta.showAndWait();
                break;
        }
    }

    private void configurarDragAndDrop(Label pieceLabel) {
        // Configurar el origen del drag (pieza que se arrastra)
        pieceLabel.setOnDragDetected(event -> {
            Dragboard db = pieceLabel.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();

            // Guardamos información sobre la pieza que estamos arrastrando
            // Usamos el índice de la pieza en el GridPane
            Integer rowIndex = GridPane.getRowIndex(pieceLabel);
            Integer colIndex = GridPane.getColumnIndex(pieceLabel);
            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            content.putString(rowIndex + "," + colIndex);
            db.setContent(content);

            event.consume();
        });

        // Configurar el destino del drag (donde se suelta la pieza)
        pieceLabel.setOnDragOver(event -> {
            // Permitir la operación de drop
            if (event.getGestureSource() != pieceLabel &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        // Manejar el evento de drop
        pieceLabel.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                // Obtenemos las coordenadas de origen
                String[] sourceIndices = db.getString().split(",");
                int sourceRow = Integer.parseInt(sourceIndices[0]);
                int sourceCol = Integer.parseInt(sourceIndices[1]);

                // Obtenemos las coordenadas de destino
                Integer targetRow = GridPane.getRowIndex(pieceLabel);
                Integer targetCol = GridPane.getColumnIndex(pieceLabel);
                if (targetRow == null) targetRow = 0;
                if (targetCol == null) targetCol = 0;

                // Intercambiar las piezas
                intercambiarPiezas(sourceRow, sourceCol, targetRow, targetCol);

                success = true;

                // Verificar si el rompecabezas está completo
                if (verificarRompecabezasCompleto()) {
                    tiempoFinal = System.currentTimeMillis() - tiempoInicion;
                    mostrarAlertaCompletado(tiempoFinal);
                    registrarTiempos(tiempoFinal);
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void intercambiarPiezas(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        // Obtenemos referencias a las etiquetas
        Label sourceLabel = obtenerLabelEnPosicion(piezas, sourceRow, sourceCol);
        Label targetLabel = obtenerLabelEnPosicion(piezas, targetRow, targetCol);

        if (sourceLabel != null && targetLabel != null) {
            // Guardamos los gráficos (imágenes)
            ImageView sourceImage = (ImageView) sourceLabel.getGraphic();
            ImageView targetImage = (ImageView) targetLabel.getGraphic();

            // Guardamos los metadatos
            Object sourceData = sourceLabel.getUserData();
            Object targetData = targetLabel.getUserData();

            // Intercambiamos las imágenes y los metadatos
            sourceLabel.setGraphic(targetImage);
            targetLabel.setGraphic(sourceImage);

            sourceLabel.setUserData(targetData);
            targetLabel.setUserData(sourceData);

            // Actualizamos la matriz imagenes si la estamos utilizando
            imagenes[sourceRow][sourceCol] = sourceLabel;
            imagenes[targetRow][targetCol] = targetLabel;
        }
       }

    private Label obtenerLabelEnPosicion(GridPane grid, int row, int col) {
        for (Node node : grid.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);

            if (nodeRow == null) nodeRow = 0;
            if (nodeCol == null) nodeCol = 0;

            if (nodeRow == row && nodeCol == col && node instanceof Label) {
                return (Label) node;
            }
        }
        return null;
    }

    private boolean verificarRompecabezasCompleto() {
        // Recorrer todas las piezas y verificar si están en la posición correcta
        for (Node node : piezas.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;

                // Obtener la posición actual en el grid
                Integer currentRow = GridPane.getRowIndex(label);
                Integer currentCol = GridPane.getColumnIndex(label);
                if (currentRow == null) currentRow = 0;
                if (currentCol == null) currentCol = 0;

                // Obtener la posición correcta almacenada en userData
                String[] correctPosition = ((String) label.getUserData()).split(",");
                int correctRow = Integer.parseInt(correctPosition[0]);
                int correctCol = Integer.parseInt(correctPosition[1]);

                if (currentRow != correctRow || currentCol != correctCol) {
                    return false;
                }
            }
        }
        cuentador.stop();
        return true;
    }

    private void mostrarAlertaCompletado(long tiempo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("¡Felicidades!");
        alert.setHeaderText("¡Rompecabezas completado!");
        alert.setContentText("Has completado el rompecabezas en " + (tiempo / 1000) + " segundos.");
        alert.showAndWait();
    }

    private void registrarTiempos(long tiempo) {
        int a = 0;

            File dirResources = new File("tiempos");
            File archivo = new File("tiempos/tiempos.txt");
        try {
            // Crear directorio si no existe
            if (!dirResources.exists()) {
                dirResources.mkdirs();
            }

            // Ruta al archivo de tiempos, usando ruta relativa simple
            System.out.println("Guardando tiempo en: " + archivo.getAbsolutePath());
            PrintWriter writer = new PrintWriter(new FileWriter(archivo,true));
            writer.print("Duracion del intento: ");
            writer.print("\t");
            writer.print(tiempo);
            writer.print(" ms");
            writer.println();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar tiempo: " + e.getMessage());

            // Mostrar alerta de error
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al guardar tiempo");
            alert.setContentText("No se pudo guardar el tiempo en el archivo.\nDetalle: " + e.getMessage());
            alert.showAndWait();
        }
        mostrarTiempo();

        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado correctamente después de la ejecución.");
            } else {
                System.err.println("No se pudo eliminar el archivo después de la ejecución.");
            }
        }
    }

    public void mostrarTiempo()
    {
        String[] numero;
        FileReader archivo;
        String aux;
        ObservableList<String> tiempIntentos =FXCollections.observableArrayList();
        Stage ventana = new Stage();
        Scene escena;
        try {
            archivo = new FileReader("tiempos/tiempos.txt");
            Scanner lectura = new Scanner(archivo);
            intento = new TableView<String>();
            TableColumn<String,String> tbIntentos = new TableColumn<>("Intentos");
            while (lectura.hasNextLine())
            {
                aux = lectura.nextLine();
                numero = aux.split("\s");
                aux = numero[2]+": " +String.valueOf(Double.parseDouble(numero[3])/1000)+" s";
                tiempIntentos.add(aux);
            }

            tbIntentos.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));

            intento.getColumns().add(tbIntentos);
            intento.setItems(tiempIntentos);
        } catch (Exception e) {
            e.printStackTrace();
        }
        escena = new Scene(intento);
        ventana.setTitle("Historial de intenntos");
        ventana.setScene(escena);
        ventana.show();
    }

    public void contador() {
        IntegerProperty contador = new SimpleIntegerProperty(0);
        tiempo.textProperty().bind(contador.asString());

        cuentador = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            contador.set(contador.get() + 1);
        }));

        cuentador.setCycleCount(Timeline.INDEFINITE);
        cuentador.play();
    }

    public String[][] generarCuadroCoordenadas(int fila, int columna, String[][] cuadrito) {
        ArrayList<String> todasLasCoordenadas = new ArrayList<>();

        // Generamos todas las coordenadas posibles
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                todasLasCoordenadas.add(i + "," + j);
            }
        }
        Collections.shuffle(todasLasCoordenadas);
        int indice = 0;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                cuadrito[i][j] = todasLasCoordenadas.get(indice++);
            }
        }
        return cuadrito;
    }
}