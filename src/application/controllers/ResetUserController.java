package application.controllers;

import application.models.User;
import application.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class ResetUserController {

    @FXML
    private TextField usernameField;
    @FXML
    private DatePicker expiryDatePicker;
    @FXML
    private TextField expiryTimeField; // Format: HH:mm
    @FXML
    private Button resetButton;
    @FXML
    private Label infoLabel;
    @FXML
    private Label errorLabel;

    @FXML
    private void handleReset(ActionEvent event) {
        String username = usernameField.getText();
        Map<String, User> users = DataManager.getUsers();

        if (users.containsKey(username)) {
            User user = users.get(username);

            // Generate one-time password (for simplicity, use a fixed value or generate randomly)
            String oneTimePassword = "TempPassword123"; // In practice, generate a secure random password
            user.setOneTimePassword(true);

            // Set expiry date and time
            LocalDateTime expiryDateTime;
            try {
                LocalDateTime date = expiryDatePicker.getValue().atStartOfDay();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                LocalDateTime time = LocalDateTime.parse(expiryDatePicker.getValue() + "T" + expiryTimeField.getText(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                expiryDateTime = date.withHour(time.getHour()).withMinute(time.getMinute());
            } catch (Exception e) {
                errorLabel.setText("Invalid date/time format.");
                return;
            }

            user.setOtpExpiry(expiryDateTime);
            DataManager.saveUserData();

            // Inform the admin (or send an email to the user in a real application)
            infoLabel.setText("One-time password set. Inform the user to log in and reset their password.");
            errorLabel.setText("");
        } else {
            errorLabel.setText("User not found.");
        }
    }
}
