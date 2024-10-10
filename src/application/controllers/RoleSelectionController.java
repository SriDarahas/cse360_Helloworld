package application.controllers;

import application.models.Role;
import application.models.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RoleSelectionController {

    @FXML
    private ListView<Role> rolesListView;
    @FXML
    private Button selectRoleButton;
    @FXML
    private Label errorLabel;

    private User currentUser;

    public void setUser(User user) {
        this.currentUser = user;
        rolesListView.setItems(FXCollections.observableArrayList(user.getRoles()));
    }

    @FXML
    private void handleSelectRole(ActionEvent event) {
        Role selectedRole = rolesListView.getSelectionModel().getSelectedItem();
        if (selectedRole != null) {
            try {
                switch (selectedRole) {
                    case ADMIN:
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/AdminHome.fxml"));
                        Parent root = loader.load();

                        AdminHomeController controller = loader.getController();
                        controller.setCurrentUser(currentUser);

                        Stage stage = (Stage) selectRoleButton.getScene().getWindow();
                        stage.setTitle("Admin Home");
                        stage.setScene(new Scene(root));
                        stage.show();
                        break;
                    case STUDENT:
                        // Load Student Home Page (e.g., StudentHome.fxml)
                        // Similar to above
                        break;
                    case INSTRUCTOR:
                        // Load Instructor Home Page (e.g., InstructorHome.fxml)
                        // Similar to above
                        break;
                    default:
                        errorLabel.setText("Unknown role selected.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                errorLabel.setText("An error occurred.");
            }
        } else {
            errorLabel.setText("Please select a role.");
        }
    }
}
