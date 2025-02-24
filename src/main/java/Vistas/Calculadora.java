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
        escena = new Scene(vbox);
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
                if(teclas[pos].equals("+")) {
                    btnTeclado[i][j].setId("fontButton");
                    //esto es una asignacion a un [unico elemento de la itnerfaz
                    //btnTeclado[i][j].setStyle("-fx-background-color: rgbs(60,60,60,60,60)");
                }btnTeclado[i][j].setOnAction(actionEvent -> EventoTeclado(teclas[finalPos]));
                btnTeclado[i][j].setPrefSize(50,50);
                gdspTeclado.add(btnTeclado[i][j],j,i);
                pos++;
            }
        }

    }

    private void  EventoTeclado(String tecla) {
        txtDisplay.appendText(tecla);
        if (tecla == "=")
        {
            operacion = txtDisplay.getText();
            realizarOperacion(operacion);
        }
    }

    private void realizarOperacion(String operaciones)
    {

    }

    public Calculadora()
    {
        crearUI();
        this.setScene(escena);
        //como va a ser un stage, el thss nomas es para ahorrar variables
        this.setTitle("Calculadora");
        this.show();
    }
}
