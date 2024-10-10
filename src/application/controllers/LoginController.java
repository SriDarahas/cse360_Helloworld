package application.controllers;

import application.models.*;
import application.utils.DataManager;
import application.utils.PasswordUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.Map;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private TextField inviteCodeField;
    @FXML private Button loginButton;
    @FXML private Button useInviteButton;
    @FXML private Label errorLabel;

    private Map<String, User> users;

    public void initialize() {
        DataManager.loadUserData();
        users = DataManager.getUsers();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (users.containsKey(username)) {
            User user = users.get(username);
            try {
                byte[] hashedPassword = PasswordUtil.hashPassword(password, user.getSalt());
                if (MessageDigest.isEqual(hashedPassword, user.getPasswordHash())) {
                    if (user.isOneTimePassword()) {
                        if (LocalDateTime.now().isAfter(user.getOtpExpiry())) {
                            errorLabel.setText("One-time password expired.");
                            return;
                        }
                        // Proceed to reset password
                        // Load ResetPassword.fxml
                    } else {
                        if (user.getName() == null) {
                            // Load FinishSetup.fxml
                        } else {
                            if (user.getRoles().size() > 1) {
                                // Load RoleSelection.fxml
                            } else {
                                // Load Home Page for the single role
                            }
                        }
                    }
                } else {
                    errorLabel.setText("Incorrect password.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("An error occurred.");
            }
        } else {
            errorLabel.setText("User does not exist.");
        }
    }

    @FXML
    private void handleUseInvite(ActionEvent event) {
        String code = inviteCodeField.getText();
        DataManager.loadInviteData();
        Map<String, InvitationCode> invites = DataManager.getInvites();
        if (invites.containsKey(code)) {
            InvitationCode invite = invites.get(code);
            if (!invite.isUsed()) {
                // Proceed to Signup.fxml
            } else {
                errorLabel.setText("Invitation code has been used.");
            }
        } else {
            errorLabel.setText("Invalid invitation code.");
        }
    }
}
