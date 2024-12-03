import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Hovedklassen, der starter JavaFX-applikationen
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Opretter en instans af TipCalculator-klassen
        TipCalculator calculator = new TipCalculator();

        // Opretter en Scene og tilføjer brugergrænsefladen fra TipCalculator
        Scene scene = new Scene(calculator.getUI(), 400, 400);

        // Tilføjer en stylesheet til at style brugergrænsefladen
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Indstiller vinduets titel
        primaryStage.setTitle("Tipberegner");

        // Tilføjer scenen til vinduet
        primaryStage.setScene(scene);

        // Viser vinduet
        primaryStage.show();
    }

    // Main-metoden, som starter applikationen
    public static void main(String[] args) {
        launch(args); // Starter JavaFX-applikationen
    }
}
