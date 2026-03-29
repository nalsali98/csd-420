import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Name: Noor Alsalihi
 * Course: CSD420-340A Advanced Java Programming
 * Assignment: Module 1.3 Programming Assignment
 *C:\Users\19418\IdeaProjects\untitled5\src\main\java\Alsalihi_Mod1_csd420.java
 * 
 * This JavaFX program displays four randomly selected cards from a deck of 52.
 * The card image files are stored in a "cards" subdirectory.
 * A Refresh button displays four different random cards.
 */

public class Alsalihi_Mod1_csd420 extends Application {

    private final ImageView[] cardViews = new ImageView[4];

    @Override
    public void start(Stage primaryStage) {
        HBox cardBox = new HBox(15);
        cardBox.setAlignment(Pos.CENTER);

        for (int i = 0; i < 4; i++) {
            cardViews[i] = new ImageView();
            cardViews[i].setFitWidth(100);
            cardViews[i].setFitHeight(150);
            cardViews[i].setPreserveRatio(true);
            cardBox.getChildren().add(cardViews[i]);
        }

        Button refreshButton = new Button("Refresh");

        // Lambda expression used here
        refreshButton.setOnAction(e -> displayRandomCards());

        BorderPane root = new BorderPane();
        root.setCenter(cardBox);

        HBox buttonBox = new HBox(refreshButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setStyle("-fx-padding: 15;");
        root.setBottom(buttonBox);

        displayRandomCards();

        Scene scene = new Scene(root, 500, 250);
        primaryStage.setTitle("Random Playing Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayRandomCards() {
        List<Integer> deck = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        Collections.shuffle(deck);

        for (int i = 0; i < 4; i++) {
            String imagePath = getClass().getResource("/cards/" + deck.get(i) + ".png").toExternalForm();
            cardViews[i].setImage(new Image(imagePath));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}