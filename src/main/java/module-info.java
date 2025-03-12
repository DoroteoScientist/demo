module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.j;
    requires java.sql;
    requires jdk.dynalink;
    requires java.desktop;

    exports com.example.demo.Componentes;
    exports com.example.demo;
    exports Modelos;
    opens com.example.demo to javafx.fxml;
    opens Modelos to javafx.fxml;
    opens com.example.demo.Componentes to javafx.fxml;

}