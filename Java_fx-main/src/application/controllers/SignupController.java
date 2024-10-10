package application.controllers;

import application.models.*;
import application.utils.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

public class SignupController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label errorLabel;

    private InvitationCode invitationCode;

    public void setInvitationCode(InvitationCode code) {
        this.invitationCode = code;
    }

    @FXML
    private void handleSignup(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (!password.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        Map<String, User> users = DataManager.getUsers();
        if (users.containsKey(username)) {
            errorLabel.setText("Username already exists.");
            return;
        }

        try {
            byte[] salt = PasswordUtil.generateSalt();
            byte[] hashedPassword = PasswordUtil.hashPassword(password, salt);

            User user = new User();
            user.setUsername(username);
            user.setPasswordHash(hashedPassword);
            user.setSalt(salt);
            user.setRoles(invitationCode.getRoles());

            users.put(username, user);
            DataManager.saveUserData();

            // Invalidate invitation code
            invitationCode.setUsed(true);
            DataManager.saveInviteData();

            // Redirect to Login.fxml
        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred.");
        }
    }
}
