package application.controllers;

import application.models.Role;
import application.models.User;
import application.utils.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Map;

public class ManageRolesController {

    @FXML
    private TextField usernameField;
    @FXML
    private ListView<Role> rolesListView;
    @FXML
    private Button addRoleButton;
    @FXML
    private Button removeRoleButton;
    @FXML
    private Label errorLabel;

    public void initialize() {
        rolesListView.setItems(FXCollections.observableArrayList(Role.values()));
        rolesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleAddRole(ActionEvent event) {
        String username = usernameField.getText();
        Map<String, User> users = DataManager.getUsers();

        if (users.containsKey(username)) {
            User user = users.get(username);
            user.getRoles().addAll(rolesListView.getSelectionModel().getSelectedItems());
            DataManager.saveUserData();
            errorLabel.setText("Roles added successfully.");
        } else {
            errorLabel.setText("User not found.");
        }
    }

    @FXML
    private void handleRemoveRole(ActionEvent event) {
        String username = usernameField.getText();
        Map<String, User> users = DataManager.getUsers();

        if (users.containsKey(username)) {
            User user = users.get(username);
            user.getRoles().removeAll(rolesListView.getSelectionModel().getSelectedItems());
            DataManager.saveUserData();
            errorLabel.setText("Roles removed successfully.");
        } else {
            errorLabel.setText("User not found.");
        }
    }
}
