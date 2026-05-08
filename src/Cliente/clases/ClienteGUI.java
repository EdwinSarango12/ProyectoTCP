package Cliente.clases;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClienteGUI extends Application {

    private Cliente clienteTcp;

    @Override
    public void start(Stage primaryStage) {
        clienteTcp = new Cliente();

        primaryStage.setTitle("Registro de Asistencia - Empleados");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // IP Configuration
        Label ipLabel = new Label("IP del Servidor:");
        grid.add(ipLabel, 0, 0);
        TextField ipTextField = new TextField("127.0.0.1");
        grid.add(ipTextField, 1, 0);

        // Employee Name
        Label nameLabel = new Label("Nombre Empleado:");
        grid.add(nameLabel, 0, 1);
        TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);

        // Action Buttons
        Button btnIngreso = new Button("Ingreso");
        Button btnSalidaAlmuerzo = new Button("Salida Almuerzo");
        Button btnEntradaAlmuerzo = new Button("Entrada Almuerzo");
        Button btnSalida = new Button("Salida");

        // Styling buttons to be a bit larger
        btnIngreso.setMinWidth(120);
        btnSalidaAlmuerzo.setMinWidth(120);
        btnEntradaAlmuerzo.setMinWidth(120);
        btnSalida.setMinWidth(120);

        HBox hbBtnTop = new HBox(10);
        hbBtnTop.setAlignment(Pos.CENTER);
        hbBtnTop.getChildren().addAll(btnIngreso, btnSalidaAlmuerzo);
        grid.add(hbBtnTop, 0, 2, 2, 1);

        HBox hbBtnBottom = new HBox(10);
        hbBtnBottom.setAlignment(Pos.CENTER);
        hbBtnBottom.getChildren().addAll(btnEntradaAlmuerzo, btnSalida);
        grid.add(hbBtnBottom, 0, 3, 2, 1);

        // Output Text Area
        Label outputLabel = new Label("Mensajes del Servidor:");
        grid.add(outputLabel, 0, 4);
        TextArea outputTextArea = new TextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setPrefRowCount(5);
        outputTextArea.setWrapText(true);
        grid.add(outputTextArea, 0, 5, 2, 1);

        // Event Handlers
        btnIngreso.setOnAction(
                e -> registrarAccion(ipTextField.getText(), nameTextField.getText(), "INGRESO", outputTextArea));
        btnSalidaAlmuerzo.setOnAction(e -> registrarAccion(ipTextField.getText(), nameTextField.getText(),
                "SALIDA_ALMUERZO", outputTextArea));
        btnEntradaAlmuerzo.setOnAction(e -> registrarAccion(ipTextField.getText(), nameTextField.getText(),
                "ENTRADA_ALMUERZO", outputTextArea));
        btnSalida.setOnAction(
                e -> registrarAccion(ipTextField.getText(), nameTextField.getText(), "SALIDA", outputTextArea));

        Scene scene = new Scene(grid, 450, 350);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registrarAccion(String ip, String nombre, String tipo, TextArea output) {
        if (nombre == null || nombre.trim().isEmpty()) {
            output.appendText("Por favor, ingrese un nombre de empleado.\n");
            return;
        }
        if (ip == null || ip.trim().isEmpty()) {
            output.appendText("Por favor, ingrese la IP del servidor.\n");
            return;
        }

        clienteTcp.setIpServidor(ip);
        try {
            String respuesta = clienteTcp.enviar(nombre.trim(), tipo);
            output.appendText(tipo + ": " + respuesta + "\n");
        } catch (Exception ex) {
            output.appendText("Error al conectar con el servidor: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
