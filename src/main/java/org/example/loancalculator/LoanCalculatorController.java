package org.example.loancalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoanCalculatorController {

    //Declaring the text fields and buttons
    @FXML
    private TextField annualRateField;

    @FXML
    private Button calculateButton;

    @FXML
    private Button clearButton;
    @FXML
    private TextField loanAmntField;

    @FXML
    private TextField mthlyPaymentField;

    @FXML
    private TextField numOfYearsField;

    @FXML
    private TextField totalPaymentField;

    @FXML
    private void initialize() {
        // This the first method that is called by javaFx when the fxml file is loaded so
        // im using it to make result fields non-editable by default
        mthlyPaymentField.setEditable(false);
        totalPaymentField.setEditable(false);

    }

    //Created this method to check if all input fields are filled and contain valid values.
    //It returns true if input is valid, false otherwise.
    private boolean validateInput() {
        if (loanAmntField.getText().isEmpty() || annualRateField.getText().isEmpty() || numOfYearsField.getText().isEmpty()) {
            showError("All textboxes must be filled.");
            return false;
        }
        try {
            double loanAmount = Double.parseDouble(loanAmntField.getText());
            double annualRate = Double.parseDouble(annualRateField.getText());
            int numberOfYears = Integer.parseInt(numOfYearsField.getText());

            if (loanAmount <= 0 || annualRate <= 0 || numberOfYears <= 0) {
                showError("All the numbers must be positive.");
                return false;
            }
        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter numeric values.");
            return false;
        }
        return true;
    }

    //Created this method to  display an error messages using the JavaFX Alert
    private void showError(String message) {
        mthlyPaymentField.setText("Error occurred");
        totalPaymentField.setText("Error occurred");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void calculateOnAction(ActionEvent event) {
        if (!validateInput()) {
            return;
        }
        try {
            // Geting the values from all the text fields
            //gets the text from loanAmntField and converting it to a double.
            double loanAmount = Double.parseDouble(loanAmntField.getText());
            //This is similar to the previous line, but I also divides the
            // result by 100 to convert the percentage to a decimal for future calculations.
            double annualRate = Double.parseDouble(annualRateField.getText()) / 100;
            //converting the text from numOfYearsField to an integer using Integer.parseInt().
            int numberOfYears = Integer.parseInt(numOfYearsField.getText());

            // Calculating the monthly interest rate
            // by dividing the annual rate by 12 (months in a year).
            double monthlyRate = annualRate / 12;

            // Calculating the total number of payments by multiplying the number of years by 12.
            int numberOfPayments = numberOfYears * 12;

            // Calculating the monthly payment using this formula that uses the loan amortization formula.
            double monthlyPayment = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments))
                    / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);

            // Calculating the total payment by multiplying the monthly payment by the number of payments.
            double totalPayment = monthlyPayment * numberOfPayments;

            // Setting results to text fields
            mthlyPaymentField.setText(String.format("$%.2f", monthlyPayment));
            totalPaymentField.setText(String.format("$%.2f", totalPayment));

            //This is the catch block. If a NumberFormatException occurs in the try block, it runs this code.
            //A NumberFormatException, occurs when parsing strings to numbers.
            //amd it also calls the showError method to display an error message.
        } catch (NumberFormatException e) {
            showError("An unexpected error occurred.");
        }
    }

    @FXML
        //This method is used to clear all the text boxes
    void clearFields(ActionEvent event) {
        loanAmntField.clear();
        annualRateField.clear();
        numOfYearsField.clear();
        mthlyPaymentField.clear();
        totalPaymentField.clear();
    }
}