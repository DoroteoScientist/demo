package Vistas;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Rompecabezas extends Stage {

    Scene escena;

    private ToolBar menu;
    private VBox vBox;
    private Button btnEmpezar, btnHistorial;
    private GridPane tablerito;
    private MenuBar practica3;
    private MenuItem chico, medio, grande;
    private Label[][] imagenes;
    private Menu opciones;
    private TextField opcionInicio;
    private GridPane piezas;
    private GridPane piezasOriginales;
    private long tiempoInicion, tiempoFinal;
    private RandomAccessFile regTiempos;

    // Elementos para el contador de tiempo
    private Label tiempoLabel;
    private Timeline timeline;
    private int segundos = 0;
    private boolean juegoEnCurso = false;

    public Rompecabezas() {
        CrearUI();
        this.setTitle("Rompecabezas");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI() {
        piezas = new GridPane();
        piezasOriginales = new GridPane();

        chico = new MenuItem("Piezas chicas");
        chico.setOnAction(actionEvent ->
                opcionInicio.setText("Piezas chicas"));

        medio = new MenuItem("Piezas medianas");
        medio.setOnAction(actionEvent ->
                opcionInicio.setText("Piezas medianas"));

        grande = new MenuItem("Piezas grandes");
        grande.setOnAction(actionEvent ->
                opcionInicio.setText("Piezas grandes"));

        opcionInicio = new TextField("");
        opcionInicio.setEditable(false);

        btnEmpezar = new Button("Empezar");
        btnEmpezar.setOnAction(actionEvent -> {
            if (!opcionInicio.getText().isEmpty()) {
                tiempoInicion = System.currentTimeMillis();
                reiniciarContador();
                iniciarContador();
                juegoEnCurso = true;
                tablero(opcionInicio.getText());
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Selección requerida");
                alert.setHeaderText("Selecciona un tamaño de piezas");
                alert.setContentText("Por favor, selecciona un tamaño de piezas antes de empezar.");
                alert.showAndWait();
            }
        });

        btnHistorial = new Button("Historial");
        btnHistorial.setOnAction(actionEvent -> mostrarHistorial());

        opciones = new Menu("Opciones de inicio");
        opciones.getItems().addAll(chico, medio, grande);
        practica3 = new MenuBar();
        practica3.getMenus().addAll(opciones);

        // Crear el contador de tiempo
        tiempoLabel = new Label("Tiempo: 00:00");
        tiempoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        // Crear HBox para el contador
        HBox contadorBox = new HBox(10);
        contadorBox.setAlignment(Pos.CENTER_RIGHT);
        contadorBox.getChildren().add(tiempoLabel);

        menu = new ToolBar(btnEmpezar, btnHistorial, practica3, opcionInicio);
        menu.getItems().add(contadorBox);

        vBox = new VBox(menu, piezas);
        escena = new Scene(vBox, 600, 600);
    }

    private void iniciarContador() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            segundos++;
            actualizarContador();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void detenerContador() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void reiniciarContador() {
        detenerContador();
        segundos = 0;
        actualizarContador();
    }

    private void actualizarContador() {
        int minutos = segundos / 60;
        int segs = segundos % 60;
        tiempoLabel.setText(String.format("Tiempo: %02d:%02d", minutos, segs));
    }

    public void tablero(String opcion) {
        piezas.getChildren().clear();
        piezasOriginales.getChildren().clear();

        switch (opcion) {
            case "Piezas chicas":
                imagenes = new Label[4][4];

                String[][] coordenadasMezcladas = new String[4][4];
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
                        label.setUserData(rowCoord + "," + colCoord);

                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }

                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        String imagePath = "/image/puzzle1/row-" + (i + 1) + "-column-" + (j + 1) + ".jpg";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(100);
                        imageView.setFitHeight(100);

                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setUserData(i + "," + j);

                        piezasOriginales.add(label, j, i);
                    }
                }
                break;

            case "Piezas medianas":
                // Implementar para tamaño mediano (3x3)
                imagenes = new Label[3][3];

                coordenadasMezcladas = new String[3][3];
                coordenadasMezcladas = generarCuadroCoordenadas(3, 3, coordenadasMezcladas);

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        String[] coords = coordenadasMezcladas[i][j].split(",");
                        int rowCoord = Integer.parseInt(coords[0]);
                        int colCoord = Integer.parseInt(coords[1]);

                        String imagePath = "/image/puzzle1/row-" + (rowCoord + 1) + "-column-" + (colCoord + 1) + ".jpg";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(130);
                        imageView.setFitHeight(130);

                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setUserData(rowCoord + "," + colCoord);

                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }
                break;

            case "Piezas grandes":
                // Implementar para tamaño grande (2x2)
                imagenes = new Label[2][2];

                coordenadasMezcladas = new String[2][2];
                coordenadasMezcladas = generarCuadroCoordenadas(2, 2, coordenadasMezcladas);

                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 2; j++) {
                        String[] coords = coordenadasMezcladas[i][j].split(",");
                        int rowCoord = Integer.parseInt(coords[0]);
                        int colCoord = Integer.parseInt(coords[1]);

                        String imagePath = "/image/puzzle1/row-" + (rowCoord + 1) + "-column-" + (colCoord + 1) + ".jpg";
                        ImageView imageView = new ImageView(getClass().getResource(imagePath).toString());
                        imageView.setFitWidth(200);
                        imageView.setFitHeight(200);

                        Label label = new Label();
                        label.setGraphic(imageView);
                        label.setUserData(rowCoord + "," + colCoord);

                        configurarDragAndDrop(label);

                        imagenes[i][j] = label;
                        piezas.add(label, j, i);
                    }
                }
                break;
        }
    }

    private void configurarDragAndDrop(Label pieceLabel) {
        pieceLabel.setOnDragDetected(event -> {
            if (!juegoEnCurso) return;

            Dragboard db = pieceLabel.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent content = new ClipboardContent();

            Integer rowIndex = GridPane.getRowIndex(pieceLabel);
            Integer colIndex = GridPane.getColumnIndex(pieceLabel);
            if (rowIndex == null) rowIndex = 0;
            if (colIndex == null) colIndex = 0;

            content.putString(rowIndex + "," + colIndex);
            db.setContent(content);

            event.consume();
        });

        pieceLabel.setOnDragOver(event -> {
            if (!juegoEnCurso) return;

            if (event.getGestureSource() != pieceLabel &&
                    event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        pieceLabel.setOnDragDropped(event -> {
            if (!juegoEnCurso) return;

            Dragboard db = event.getDragboard();
            boolean success = false;

            if (db.hasString()) {
                String[] sourceIndices = db.getString().split(",");
                int sourceRow = Integer.parseInt(sourceIndices[0]);
                int sourceCol = Integer.parseInt(sourceIndices[1]);

                Integer targetRow = GridPane.getRowIndex(pieceLabel);
                Integer targetCol = GridPane.getColumnIndex(pieceLabel);
                if (targetRow == null) targetRow = 0;
                if (targetCol == null) targetCol = 0;

                intercambiarPiezas(sourceRow, sourceCol, targetRow, targetCol);

                success = true;

                if (verificarRompecabezasCompleto()) {
                    tiempoFinal = System.currentTimeMillis() - tiempoInicion;
                    juegoEnCurso = false;
                    detenerContador();
                    mostrarAlertaCompletado(tiempoFinal);
                    registrarTiempos(tiempoFinal);
                }
            }

            event.setDropCompleted(success);
            event.consume();
        });
    }

    private void intercambiarPiezas(int sourceRow, int sourceCol, int targetRow, int targetCol) {
        Label sourceLabel = obtenerLabelEnPosicion(piezas, sourceRow, sourceCol);
        Label targetLabel = obtenerLabelEnPosicion(piezas, targetRow, targetCol);

        if (sourceLabel != null && targetLabel != null) {
            ImageView sourceImage = (ImageView) sourceLabel.getGraphic();
            ImageView targetImage = (ImageView) targetLabel.getGraphic();

            Object sourceData = sourceLabel.getUserData();
            Object targetData = targetLabel.getUserData();

            sourceLabel.setGraphic(targetImage);
            targetLabel.setGraphic(sourceImage);

            sourceLabel.setUserData(targetData);
            targetLabel.setUserData(sourceData);

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
        for (Node node : piezas.getChildren()) {
            if (node instanceof Label) {
                Label label = (Label) node;

                Integer currentRow = GridPane.getRowIndex(label);
                Integer currentCol = GridPane.getColumnIndex(label);
                if (currentRow == null) currentRow = 0;
                if (currentCol == null) currentCol = 0;

                String[] correctPosition = ((String) label.getUserData()).split(",");
                int correctRow = Integer.parseInt(correctPosition[0]);
                int correctCol = Integer.parseInt(correctPosition[1]);

                if (currentRow != correctRow || currentCol != correctCol) {
                    return false;
                }
            }
        }
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
        try {
            File dirTiempos = new File("tiempos");
            if (!dirTiempos.exists()) {
                dirTiempos.mkdirs();
            }

            File archivo = new File("tiempos/tiempos.dat");
            System.out.println("Guardando tiempo en: " + archivo.getAbsolutePath());

            // Guardar también la fecha actual
            Date fechaActual = new Date();
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fechaFormateada = formatoFecha.format(fechaActual);

            // Guardar el tipo de rompecabezas
            String tipoRompecabezas = opcionInicio.getText();

            regTiempos = new RandomAccessFile(archivo, "rw");
            regTiempos.seek(regTiempos.length());

            // Formato: tiempo,fecha,tipo
            String registro = tiempo + "," + fechaFormateada + "," + tipoRompecabezas + "\n";
            regTiempos.writeBytes(registro);

            regTiempos.close();
            System.out.println("Tiempo guardado correctamente: " + tiempo + " ms");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al guardar tiempo: " + e.getMessage());

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error al guardar tiempo");
            alert.setContentText("No se pudo guardar el tiempo en el archivo.\nDetalle: " + e.getMessage());
            alert.showAndWait();
        }
    }

    private void mostrarHistorial() {
        // Crear una nueva ventana para mostrar el historial
        Stage ventanaHistorial = new Stage();
        ventanaHistorial.setTitle("Historial de Tiempos");

        // Crear una tabla para mostrar los tiempos
        TableView<RegistroTiempo> tabla = new TableView<>();

        // Definir las columnas
        TableColumn<RegistroTiempo, String> colFecha = new TableColumn<>("Fecha");
        colFecha.setCellValueFactory(cellData -> cellData.getValue().fechaProperty());
        colFecha.setPrefWidth(150);

        TableColumn<RegistroTiempo, String> colTiempo = new TableColumn<>("Tiempo");
        colTiempo.setCellValueFactory(cellData -> cellData.getValue().tiempoFormateadoProperty());
        colTiempo.setPrefWidth(100);

        TableColumn<RegistroTiempo, String> colTipo = new TableColumn<>("Tipo");
        colTipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
        colTipo.setPrefWidth(120);

        tabla.getColumns().addAll(colFecha, colTiempo, colTipo);

        // Cargar los datos desde el archivo
        ArrayList<RegistroTiempo> registros = cargarRegistrosTiempos();
        tabla.getItems().addAll(registros);

        // Crear botones para ordenar los registros
        Button btnOrdenarPorTiempo = new Button("Ordenar por Tiempo");
        btnOrdenarPorTiempo.setOnAction(e -> {
            registros.sort((r1, r2) -> Long.compare(r1.getTiempoRaw(), r2.getTiempoRaw()));
            tabla.getItems().clear();
            tabla.getItems().addAll(registros);
        });

        Button btnOrdenarPorFecha = new Button("Ordenar por Fecha");
        btnOrdenarPorFecha.setOnAction(e -> {
            registros.sort((r1, r2) -> r2.getFecha().compareTo(r1.getFecha()));
            tabla.getItems().clear();
            tabla.getItems().addAll(registros);
        });

        // Layouts
        HBox botonesBox = new HBox(10, btnOrdenarPorTiempo, btnOrdenarPorFecha);
        botonesBox.setPadding(new Insets(10));
        botonesBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(10);
        root.getChildren().addAll(tabla, botonesBox);
        root.setPadding(new Insets(10));

        Scene escena = new Scene(root, 400, 500);
        ventanaHistorial.setScene(escena);
        ventanaHistorial.show();
    }

    private ArrayList<RegistroTiempo> cargarRegistrosTiempos() {
        ArrayList<RegistroTiempo> registros = new ArrayList<>();

        try {
            File archivo = new File("tiempos/tiempos.dat");
            if (!archivo.exists()) {
                System.out.println("No se encontró archivo de tiempos.");
                return registros;
            }

            RandomAccessFile raf = new RandomAccessFile(archivo, "r");
            String linea;

            while ((linea = raf.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length >= 3) {
                    long tiempo = Long.parseLong(partes[0]);
                    String fecha = partes[1];
                    String tipo = partes[2];

                    registros.add(new RegistroTiempo(tiempo, fecha, tipo));
                }
            }

            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al cargar tiempos: " + e.getMessage());
        }

        return registros;
    }

    public String[][] generarCuadroCoordenadas(int fila, int columna, String[][] cuadrito) {
        ArrayList<String> todasLasCoordenadas = new ArrayList<>();

        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                todasLasCoordenadas.add(i + "," + j);
            }
        }

        Collections.shuffle(todasLasCoordenadas);

        int index = 0;
        for (int i = 0; i < fila; i++) {
            for (int j = 0; j < columna; j++) {
                cuadrito[i][j] = todasLasCoordenadas.get(index++);
            }
        }

        return cuadrito;
    }

    // Clase para representar un registro de tiempo en la tabla
    public static class RegistroTiempo {
        private long tiempoRaw;
        private String fecha;
        private String tipo;
        private String tiempoFormateado;

        public RegistroTiempo(long tiempo, String fecha, String tipo) {
            this.tiempoRaw = tiempo;
            this.fecha = fecha;
            this.tipo = tipo;

            // Formatear el tiempo en minutos:segundos
            int segundos = (int) (tiempo / 1000);
            int minutos = segundos / 60;
            int segsRestantes = segundos % 60;
            this.tiempoFormateado = String.format("%02d:%02d", minutos, segsRestantes);
        }

        public long getTiempoRaw() {
            return tiempoRaw;
        }

        public String getFecha() {
            return fecha;
        }

        public javafx.beans.property.StringProperty fechaProperty() {
            return new javafx.beans.property.SimpleStringProperty(fecha);
        }

        public javafx.beans.property.StringProperty tiempoFormateadoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tiempoFormateado);
        }

        public javafx.beans.property.StringProperty tipoProperty() {
            return new javafx.beans.property.SimpleStringProperty(tipo);
        }
    }
}