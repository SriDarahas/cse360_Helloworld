package application.controllers;

import application.models.Name;
import application.models.User;
import application.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Map;

public class FinishSetupController {
    @FXML private TextField emailField;
    @FXML private TextField firstNameField;
    @FXML private TextField middleNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField preferredNameField;
    @FXML private Label errorLabel;

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private void handleFinishSetup(ActionEvent event) {
        String email = emailField.getText();
        String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        String preferredName = preferredNameField.getText();

        // Validation (e.g., email format)

        Name name = new Name();
        name.setFirstName(firstName);
        name.setMiddleName(middleName);
        name.setLastName(lastName);
        name.setPreferredName(preferredName);

        user.setEmail(email);
        user.setName(name);

        Map<String, User> users = DataManager.getUsers();
        users.put(user.getUsername(), user);
        DataManager.saveUserData();

        // Redirect to RoleSelection.fxml or Home Page
    }
}
