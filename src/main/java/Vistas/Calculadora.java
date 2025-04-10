package Vistas;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Calculadora extends Stage
{
    private Scene escena;
    private TextField txtDisplay;
    private VBox vbox;
    private GridPane gdspTeclado;
    private Button[][] btnTeclado;
    String teclas[] = {"7","8","9","*","4","5","6","/","1","2","3","+","=","0",".","-"};
    String operacion = "";
    private String numeroActual = "";
    private String operador = "";
    private double primerNumero = 0;
    private boolean inicioOperacion = true;
    private boolean operadorUsado = false;
    private boolean esPrimerDigito = true;

    public void crearUI()
    {
        keyboard();
        txtDisplay =  new TextField("0");
        txtDisplay.setEditable(false);
        txtDisplay.setAlignment(Pos.BASELINE_RIGHT);
        vbox = new VBox(txtDisplay,gdspTeclado);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
        escena = new Scene(vbox,200,200);
        //escena = new Scene(vbox);
        //obtener estilos de archivos de la carpeta resources
        escena.getStylesheets().add(getClass().getResource("/styles/calculadora.css").toString());
    }


    public void keyboard()
    {
        btnTeclado = new Button[4][4];
        gdspTeclado = new GridPane();
        gdspTeclado.setHgap(5);
        gdspTeclado.setVgap(5);
        int pos = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                btnTeclado[i][j] = new Button(teclas[pos]);
                int finalPos = pos;
                if(teclas[pos].equals("=")) {
                    btnTeclado[i][j].setId("fontButton");
                    //esto es una asignacion a un [unico elemento de la itnerfaz
                    //btnTeclado[i][j].setStyle("-fx-background-color: rgbs(60,60,60,60,60)");
                }
                if(teclas[pos].matches("[0-9]"))
                {
                    btnTeclado[i][j].setId("anotherFontButton");
                }
                btnTeclado[i][j].setOnAction(actionEvent -> EventoTeclado(teclas[finalPos]));
                btnTeclado[i][j].setPrefSize(50,50);
                gdspTeclado.add(btnTeclado[i][j],j,i);
                pos++;
            }
        }
    }

    private void EventoTeclado(String tecla) {
        switch (tecla) {
            case "+":
            case "-":
            case "/":
            case "*":
                procesarOperador(tecla);
                break;
            case "=":
                calcularResultado();
                break;
            case ".":
                agregarDecimal();
                break;
            default:
                agregarDigito(tecla);
                break;
        }
    }

    private void agregarDigito(String digito) {
        if (inicioOperacion || txtDisplay.getText().equals("Error") || txtDisplay.getText().equals("0")) {
            txtDisplay.setText(digito);
            inicioOperacion = false;
        } else {
            txtDisplay.setText(txtDisplay.getText() + digito);
        }
        numeroActual = txtDisplay.getText();
        operadorUsado = false;
        esPrimerDigito = false;
    }

    private void agregarDecimal() {
        if (txtDisplay.getText().equals("Error")) {
            txtDisplay.setText("0.");
            numeroActual = "0.";
            inicioOperacion = false;
            operadorUsado = false;
            return;
        }

        if (inicioOperacion) {
            txtDisplay.setText("0.");
            numeroActual = "0.";
            inicioOperacion = false;
        } else if (!numeroActual.contains(".")) {
            txtDisplay.setText(txtDisplay.getText() + ".");
            numeroActual = txtDisplay.getText();
        }
        operadorUsado = false;
    }

    private void procesarOperador(String op) {
        // Si ya se usó un operador y no es un signo para números negativos o positivos
        if (operadorUsado) {
            // Si el último carácter ya es un operador, reemplazarlo con el nuevo
            String textoActual = txtDisplay.getText();
            txtDisplay.setText(textoActual.substring(0, textoActual.length() - 1) + op);
            operador = op;
            return;
        }

        // Para manejar números negativos al inicio
        if (esPrimerDigito && (op.equals("-") || op.equals("+"))) {
            if (op.equals("-")) {
                txtDisplay.setText("-");
                numeroActual = "-";
            } else {
                // Para el caso de + al inicio, no hacer nada (número positivo implícito)
                txtDisplay.setText("");
                numeroActual = "";
            }
            inicioOperacion = false;
            esPrimerDigito = false;
            return;
        }

        // Para operadores sin número previo (excepto + y -)
        if (txtDisplay.getText().isEmpty() || txtDisplay.getText().equals("Error")) {
            txtDisplay.setText("Error");
            inicioOperacion = true;
            return;
        }

        // Proceso normal para operador
        if (!inicioOperacion) {
            // Guardar el número actual si no está vacío
            if (!txtDisplay.getText().isEmpty() && !txtDisplay.getText().equals("Error")) {
                if (!operador.isEmpty()) {
                    calcularResultado();
                }
                primerNumero = Double.parseDouble(txtDisplay.getText());
                operador = op;
                // Mostrar el operador en pantalla
                txtDisplay.setText(txtDisplay.getText() + op);
                operadorUsado = true;
            }
        } else {
            // Cambiar el operador si estamos en inicio de operación
            operador = op;
            // Si hay un primer número, mostrar y continuar
            if (primerNumero != 0 || txtDisplay.getText().equals("0")) {
                txtDisplay.setText(txtDisplay.getText() + op);
                operadorUsado = true;
            } else {
                txtDisplay.setText("Error");
            }
        }
    }

    private void calcularResultado() {
        // Si no hay operador o si el último carácter es un operador, mostrar error
        String textoDisplay = txtDisplay.getText();

        // Verifica si hay un operador
        if (operador.isEmpty()) {
            return;
        }

        // Verifica si el último carácter es un operador (no hay segundo operando)
        if (operadorUsado || textoDisplay.endsWith("+") || textoDisplay.endsWith("-") ||
                textoDisplay.endsWith("*") || textoDisplay.endsWith("/")) {
            txtDisplay.setText("Error");
            inicioOperacion = true;
            primerNumero = 0;
            operador = "";
            numeroActual = "";
            operadorUsado = false;
            esPrimerDigito = true;
            return;
        }

        // Extraer el segundo número de la expresión
        int posOperador = textoDisplay.lastIndexOf(operador);

        // Si no hay operador en pantalla
        if (posOperador == -1) {
            txtDisplay.setText("Error");
            inicioOperacion = true;
            primerNumero = 0;
            operador = "";
            numeroActual = "";
            operadorUsado = false;
            esPrimerDigito = true;
            return;
        }

        String segundoNumeroStr = textoDisplay.substring(posOperador + 1);

        // Si el segundo número está vacío
        if (segundoNumeroStr.isEmpty()) {
            txtDisplay.setText("Error");
            inicioOperacion = true;
            primerNumero = 0;
            operador = "";
            numeroActual = "";
            operadorUsado = false;
            esPrimerDigito = true;
            return;
        }

        double segundoNumero = Double.parseDouble(segundoNumeroStr);
        double resultado = 0;

        switch (operador) {
            case "+":
                resultado = primerNumero + segundoNumero;
                break;
            case "-":
                resultado = primerNumero - segundoNumero;
                break;
            case "*":
                resultado = primerNumero * segundoNumero;
                break;
            case "/":
                if (segundoNumero == 0) {
                    txtDisplay.setText("Error");
                    inicioOperacion = true;
                    primerNumero = 0;
                    operador = "";
                    numeroActual = "";
                    operadorUsado = false;
                    esPrimerDigito = true;
                    return;
                }
                resultado = primerNumero / segundoNumero;
                break;
        }

        // Formatear el resultado para eliminar decimales cuando es entero
        String resultadoStr;
        if (resultado == (int) resultado) {
            resultadoStr = String.valueOf((int) resultado);
        } else {
            resultadoStr = String.valueOf(resultado);
        }

        txtDisplay.setText(resultadoStr);
        numeroActual = resultadoStr;
        primerNumero = resultado;
        operador = "";
        inicioOperacion = true;
        operadorUsado = false;
        esPrimerDigito = true;
    }

    private void realizarOperacion(String operaciones) {
        // Este método ya no es necesario con la nueva implementación
        // pero se mantiene para compatibilidad con el código base
    }

    public Calculadora() {
        crearUI();
        this.setScene(escena);
        //como va a ser un stage, el thss nomas es para ahorrar variables
        this.setTitle("Calculadora");
        this.show();
    }
}