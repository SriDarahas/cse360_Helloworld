package application.controllers;

import application.models.User;
import application.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

public class DeleteUserController {

    @FXML
    private TextField usernameField;
    @FXML
    private Button deleteButton;
    @FXML
    private Label errorLabel;

    @FXML
    private void handleDelete(ActionEvent event) {
        String username = usernameField.getText();
        Map<String, User> users = DataManager.getUsers();

        if (users.containsKey(username)) {
            // Confirmation dialog
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete User");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete user '" + username + "'?");

            ButtonType yesButton = new ButtonType("Yes");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            alert.getButtonTypes().setAll(yesButton, cancelButton);

            alert.showAndWait().ifPresent(type -> {
                if (type == yesButton) {
                    users.remove(username);
                    DataManager.saveUserData();
                    errorLabel.setText("User deleted successfully.");
                }
            });
        } else {
            errorLabel.setText("User not found.");
        }
    }
}
