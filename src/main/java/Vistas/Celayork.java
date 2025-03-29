package Vistas;
import com.example.demo.Componentes.Hilo;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Celayork extends Stage
{
    private Scene escena;
    private VBox vBox;
    private GridPane gdpCalles;
    private Button btnIniciar;
    private Label[] arrlblRutas;
    private ProgressBar[] arrRutas;
    private String[] nombre= {"Pinos","Teneria","San Juan de la Vega","Laureles","Monte Blanco"};
    private Hilo[] thrRutas;
    public Celayork()
    {
        CrearUI();
        this.setTitle("Calles de Celaya");
        this.setScene(escena);
        this.show();
    }

    public void CrearUI()
    {
        arrRutas = new ProgressBar[5];
        arrlblRutas = new Label[5];
        thrRutas = new Hilo[5];
        gdpCalles = new GridPane();
        btnIniciar = new Button("Iniciar");

        for (int i = 0; i < arrlblRutas.length; i++)
        {
            arrlblRutas[i] = new Label(nombre[i]);
            arrRutas[i] = new ProgressBar(0);
            thrRutas[i] = new Hilo(nombre[i],arrRutas[i]);
            gdpCalles.add(arrlblRutas[i],0,i);
            gdpCalles.add(arrRutas[i],1,i);
        }

        btnIniciar.setOnAction(actionEvent ->
                {
                    for (int i = 0; i < arrRutas.length; i++) {

                        thrRutas[i].start();
                    }
                }
        );

        vBox = new VBox(gdpCalles,btnIniciar);
        escena = new Scene(vBox,300,200);
    }
}