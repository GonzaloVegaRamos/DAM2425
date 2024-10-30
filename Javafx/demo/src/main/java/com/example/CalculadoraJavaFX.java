package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CalculadoraJavaFX extends Application {
    private TextField display;
    private String operator = "";
    private double firstNumber = 0;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Configurar el diseño de la calculadora
        display = new TextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 24px;");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(display, 0, 0, 4, 1);

        // Botones numéricos
        for (int i = 0; i <= 9; i++) {
            Button button = new Button(String.valueOf(i));
            button.setPrefWidth(50);   
            button.setPrefHeight(50);
            button.setOnAction(e -> appendToDisplay(button.getText()));
            grid.add(button, i % 3, 1 + (i / 3));
        }

        // Botones de operaciones
        String[] operations = {"+", "-", "*", "/"};
        for (int i = 0; i < operations.length; i++) {
            Button button = new Button(operations[i]);
            button.setPrefWidth(50);   
            button.setPrefHeight(50);
            button.setOnAction(e -> setOperator(button.getText()));
            grid.add(button, 3, i + 1);
        }

        Button clearButton = new Button("CE");
        clearButton.setPrefWidth(50);
        clearButton.setPrefHeight(50);
        clearButton.setOnAction(e -> display.clear()); 
        grid.add(clearButton, 2, 4);

        // Botón de igual
        Button equalsButton = new Button("=");
        equalsButton.setPrefWidth(50);   // Ajusta el ancho del botón
        equalsButton.setPrefHeight(50);
        equalsButton.setOnAction(e -> calculateResult());
        grid.add(equalsButton, 1, 4, 4, 1);

        // Configurar la escena y el escenario
        Scene scene = new Scene(grid, 250, 300);
        stage.setTitle("Calculadora");
        stage.setScene(scene);
        stage.show();
    }

    private void appendToDisplay(String value) {
        display.appendText(value);
    }

    private void setOperator(String op) {
        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        display.clear();
    }

    private void calculateResult() {
        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }

        display.setText(String.valueOf(result));
        operator = "";  // Reset the operator
    }
}
