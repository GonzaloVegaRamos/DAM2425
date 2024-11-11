package com.example;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Login extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Etiqueta de información para mostrar las credenciales correctas
        Label infoLabel = new Label("Usuario: admin | Contraseña: 1234");

        // Campos de texto para usuario y contraseña
        TextField userField = new TextField();
        userField.setPromptText("Usuario");

        PasswordField passField = new PasswordField();
        passField.setPromptText("Contraseña");

        Button loginButton = new Button("Iniciar sesión");
        Label message = new Label(); // Mensaje de éxito o error

        // Acción del botón de login
        loginButton.setOnAction(e -> {
            String username = userField.getText();
            String password = passField.getText();

            // Verificar credenciales
            if (username.equals("admin") && password.equals("1234")) {
                message.setText("Inicio de sesión exitoso. ¡Bienvenido, Administrador!");
            } else if (!username.isEmpty() && !password.isEmpty()) {
                message.setText("Usuario o contraseña incorrectos");
            } else {
                message.setText("Por favor, completa ambos campos.");
            }
        });

        // Disposición de los elementos en VBox
        VBox vbox = new VBox(10, infoLabel, userField, passField, loginButton, message);
        
        // Configuración de la escena y el escenario
        primaryStage.setScene(new Scene(vbox, 300, 200));
        primaryStage.setTitle("Login Simple");
        primaryStage.show();
    }
}
