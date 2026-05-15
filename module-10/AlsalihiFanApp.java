import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;

public class AlsalihiFanApp extends Application {

    private TextField idField = new TextField();
    private TextField firstNameField = new TextField();
    private TextField lastNameField = new TextField();
    private TextField favoriteTeamField = new TextField();
    private Label messageLabel = new Label();

    private static final String URL = "jdbc:mysql://localhost:3306/databasedb";
    private static final String USER = "student1";
    private static final String PASSWORD = "pass";

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Fan Information Database");

        Button displayButton = new Button("Display");
        Button updateButton = new Button("Update");

        displayButton.setOnAction(e -> displayFan());
        updateButton.setOnAction(e -> updateFan());

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15));
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("ID:"), 0, 0);
        grid.add(idField, 1, 0);

        grid.add(new Label("First Name:"), 0, 1);
        grid.add(firstNameField, 1, 1);

        grid.add(new Label("Last Name:"), 0, 2);
        grid.add(lastNameField, 1, 2);

        grid.add(new Label("Favorite Team:"), 0, 3);
        grid.add(favoriteTeamField, 1, 3);

        grid.add(displayButton, 0, 4);
        grid.add(updateButton, 1, 4);
        grid.add(messageLabel, 1, 5);

        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    private void displayFan() {
        String sql = "SELECT * FROM fans WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int id = Integer.parseInt(idField.getText());
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                firstNameField.setText(resultSet.getString("firstname"));
                lastNameField.setText(resultSet.getString("lastname"));
                favoriteTeamField.setText(resultSet.getString("favoriteteam"));
                messageLabel.setText("Record displayed successfully.");
            } else {
                messageLabel.setText("No record found for this ID.");
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid ID.");
        } catch (SQLException ex) {
            messageLabel.setText("Database error.");
            ex.printStackTrace();
        }
    }

    private void updateFan() {
        String sql = "UPDATE fans SET firstname = ?, lastname = ?, favoriteteam = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            int id = Integer.parseInt(idField.getText());

            statement.setString(1, firstNameField.getText());
            statement.setString(2, lastNameField.getText());
            statement.setString(3, favoriteTeamField.getText());
            statement.setInt(4, id);

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                messageLabel.setText("Record updated successfully.");
            } else {
                messageLabel.setText("No record found to update.");
            }

        } catch (NumberFormatException ex) {
            messageLabel.setText("Please enter a valid ID.");
        } catch (SQLException ex) {
            messageLabel.setText("Database error.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}