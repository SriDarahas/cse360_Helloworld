package application.controllers;

import application.models.User;
import application.utils.DataManager;
import application.utils.PasswordUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ResetPasswordController {

    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmNewPasswordField;
    @FXML
    private Label errorLabel;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleResetPassword(ActionEvent event) {
        String newPassword = newPasswordField.getText();
        String confirmPassword = confirmNewPasswordField.getText();

        if (!newPassword.equals(confirmPassword)) {
            errorLabel.setText("Passwords do not match.");
            return;
        }

        try {
            byte[] newSalt = PasswordUtil.generateSalt();
            byte[] newHashedPassword = PasswordUtil.hashPassword(newPassword, newSalt);

            currentUser.setSalt(newSalt);
            currentUser.setPasswordHash(newHashedPassword);
            currentUser.setOneTimePassword(false);
            currentUser.setOtpExpiry(null);

            DataManager.saveUserData();

            // Redirect to Login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) newPasswordField.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            errorLabel.setText("An error occurred.");
        }
    }
}
