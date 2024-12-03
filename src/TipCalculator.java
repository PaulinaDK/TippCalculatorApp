import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TipCalculator {
    private TextField billAmountField; // Tekstfelt til at indtaste regningsbeløb
    private Label tipAmountLabel; // Label til at vise beregnet drikkepenge
    private Label totalAmountLabel; // Label til at vise samlet beløb
    private Slider tipSlider; // Slider til at vælge drikkepengeprocent
    private Label paymentStatusLabel; // Label til at vise betalingsstatus

    public VBox getUI() {
        VBox layout = new VBox(10); // Opret en lodret boks med 10 pixels mellemrum
        layout.setPadding(new Insets(20, 20, 40, 20)); // Sæt 20 pixels som afstand indvendigt
        layout.setPrefHeight(800);

        // Label og tekstfelt til regningsbeløb
        Label billLabel = new Label("Regningsbeløb (DKK):");
        billAmountField = new TextField();
        billAmountField.setPromptText("Indtast regningsbeløbet");

        // Slider og label til at vælge drikkepengeprocent
        Label tipLabel = new Label("Vælg din drikkepengeprocent:");
        tipSlider = new Slider(0, 30, 15);
        tipSlider.setShowTickLabels(true);
        tipSlider.setShowTickMarks(true);
        tipSlider.setMajorTickUnit(5);

        // Labels til at vise resultatet
        tipAmountLabel = new Label("Drikkepenge: 0 DKK");
        totalAmountLabel = new Label("Sum: 0 DKK");

        // Tilføj event lyttere for beregninger
        tipSlider.valueProperty().addListener((obs, oldVal, newVal) -> calculate());
        billAmountField.textProperty().addListener((obs, oldText, newText) -> calculate());

        // Opret en knap "Betal"
        Button payButton = new Button("BETAL");
        payButton.setOnAction(e -> handlePayment());

        // Label til at vise betalingsstatus
        paymentStatusLabel = new Label(); // Start uden tekst

        // Placer knappen i en HBox for justering
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.BOTTOM_LEFT);
        buttonBox.setPadding(new Insets(20, 0, 0, 0));
        buttonBox.getChildren().add(payButton);

        // Tilføj alt til layoutet
        layout.getChildren().addAll(
                billLabel, billAmountField, tipLabel, tipSlider,
                tipAmountLabel, totalAmountLabel, buttonBox, paymentStatusLabel
        );
        return layout;
    }

    private void calculate() {
        try {
            double billAmount = Double.parseDouble(billAmountField.getText());
            double tipPercentage = tipSlider.getValue();
            double tipAmount = billAmount * tipPercentage / 100;
            double totalAmount = billAmount + tipAmount;

            tipAmountLabel.setText(String.format("Drikkepenge: %.2f DKK", tipAmount));
            totalAmountLabel.setText(String.format("Sum: %.2f DKK", totalAmount));
        } catch (NumberFormatException e) {
            tipAmountLabel.setText("Drikkepenge: 0 DKK");
            totalAmountLabel.setText("Sum: 0 DKK");
        }
    }

    private void handlePayment() {
        // Opdater teksten i betalingsstatus-labelen
        paymentStatusLabel.setText("Regningen er betalt. Tak!");
    }
}
