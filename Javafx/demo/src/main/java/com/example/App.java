package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        Label label = new Label ("Ingrese su nombre:");
        TextField campoTexto = new TextField();
        Button btn = new Button("Aceptar");
        VBox layout = new VBox(10);

        Tooltip tooltip = new Tooltip("Efectivamente, soy un boton");
        btn.setTooltip(tooltip);
        DropShadow dropShadow = new DropShadow();
        btn.setEffect(dropShadow);
        
        btn.setOnMouseEntered(e ->btn.setStyle("-fx-background-color:#FF0000;"));
        btn.setOnMouseExited(e ->btn.setStyle("-fx-background-color:#fffffff;"));

        btn.setOnAction(null);


        
        // for i in range(0, 2):
        //     for j in range(0, 5):
        //         n += 1
        //         button = QPushButton(str(n-1))
        //         button.pressed.connect(lambda num=str(n-1): self.press_button(num))
        //         grid.addWidget(button, i, j)

        
        // operators = ["+", "-", "*", "="]

        // for index, symbol in enumerate(operators):
        //     button = QPushButton(symbol)
        //     button.pressed.connect(lambda sym=symbol: self.press_button(sym))
        //     grid.addWidget(button, 4, index) 

        
        layout.getChildren().addAll(label,campoTexto,btn);

        btn.setOnAction(event -> {
            String text = campoTexto.getText();
            campoTexto.clear();
            System.out.println(text);
            
        });

        scene = new Scene(layout, 300,200);

        stage.setScene(scene);
        stage.setTitle("MI pantalla");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}