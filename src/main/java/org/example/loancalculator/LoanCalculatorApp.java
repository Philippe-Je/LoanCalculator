package org.example.loancalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoanCalculatorApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            // Loading the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loan_calculator.fxml"));
            Parent root = loader.load();

            // Setting up the scene
            Scene scene = new Scene(root);

            // Set up the stage
            primaryStage.setTitle("");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            // Shows the stage
            primaryStage.show();
            /*
             created this for any exceptions that might occur
             during the setup process and prints the stack trace.
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}