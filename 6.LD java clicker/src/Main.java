
//JavaFX
//Jānis Lejnieks 211REC027 4.grupa
import javafx.application.Application;
//import javafx.scene.Group;
import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
//import javafx.scene.layout.VBox;
//import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.shape.Circle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Label label;
        GridPane gridPane;
        Scene scene;

        label = new Label("tic-tac-toe");
        gridPane = new GridPane();
        scene = new Scene(gridPane, 300, 300);
        gridPane.setStyle("-fx-grid-lines-visible: true; -fx-background-color: white;");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                StackPane ticStack = new StackPane();
                ticStack.setPrefSize(100, 100);
                ticStack.setOnMouseClicked(event -> {
                    if (event.getButton() == MouseButton.PRIMARY) {
                        makeX(ticStack);
                    } else if (event.getButton() == MouseButton.SECONDARY) {
                        makeCircle(ticStack);
                    }
                });
                gridPane.add(ticStack, col, row);
            }
        }
        EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                String msg = "X=" + (int) e.getX() + " Y=" + (int) e.getY();
                if (e.getButton() == MouseButton.PRIMARY)
                    msg += " Left Button";
                if (e.getButton() == MouseButton.SECONDARY)
                    msg += " Right Button";
                if (e.getClickCount() == 2)
                    msg += " double click";
                label.setText(msg);
            }
        };
        // Registering the event filter
        scene.addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
        primaryStage.setTitle("Graphics");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void makeX(StackPane cell) {
        Label xLabel = new Label("X");
        xLabel.setStyle("-fx-font-size: 65; -fx-text-fill: lime; -fx-font-weight: bold;");
        cell.getChildren().add(xLabel);
    }

    private void makeCircle(StackPane cell) {
        Circle circle = new Circle(22);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.LIME);
        circle.setStrokeWidth(10);
        cell.getChildren().add(circle);
    }

    public static void main(String[] args) {
        launch(args);
    }
}