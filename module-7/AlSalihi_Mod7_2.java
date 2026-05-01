import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class AlSalihi_Mod7_2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane pane = new Pane();

        Circle circle1 = new Circle(70, 80, 40);
        circle1.getStyleClass().add("plaincircle");

        Circle circle2 = new Circle(170, 80, 40);
        circle2.getStyleClass().add("plaincircle");

        Circle circle3 = new Circle(270, 80, 40);
        circle3.setId("redcircle");

        Circle circle4 = new Circle(370, 80, 40);
        circle4.setId("greencircle");

        pane.getChildren().addAll(circle1, circle2, circle3, circle4);

        Scene scene = new Scene(pane, 450, 160);
        scene.getStylesheets().add(getClass().getResource("mystyle.css").toExternalForm());

        primaryStage.setTitle("Module 7.2 JavaFX CSS");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Simple test output
        System.out.println("Circle 1 style class: " + circle1.getStyleClass());
        System.out.println("Circle 2 style class: " + circle2.getStyleClass());
        System.out.println("Circle 3 ID: " + circle3.getId());
        System.out.println("Circle 4 ID: " + circle4.getId());
    }

    public static void main(String[] args) {
        launch(args);
    }
}