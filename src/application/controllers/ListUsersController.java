package application.controllers;

import application.models.Role;
import application.models.User;
import application.utils.DataManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListUsersController {

    @FXML
    private TableView<UserData> usersTableView;
    @FXML
    private TableColumn<UserData, String> usernameColumn;
    @FXML
    private TableColumn<UserData, String> nameColumn;
    @FXML
    private TableColumn<UserData, String> rolesColumn;
    @FXML
    private Button closeButton;

    public void initialize() {
        usernameColumn.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        rolesColumn.setCellValueFactory(cellData -> cellData.getValue().rolesProperty());

        loadUserData();
    }

    private void loadUserData() {
        Map<String, User> users = DataManager.getUsers();
        List<UserData> userDataList = new ArrayList<>();

        for (User user : users.values()) {
            String username = user.getUsername();
            String name = user.getName() != null ? user.getName().getDisplayName() : "N/A";
            String roles = user.getRoles().toString();

            userDataList.add(new UserData(username, name, roles));
        }

        usersTableView.setItems(FXCollections.observableArrayList(userDataList));
    }

    @FXML
    private void handleClose(ActionEvent event) {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    // Inner class to represent user data for the table
    public static class UserData {
        private final SimpleStringProperty username;
        private final SimpleStringProperty name;
        private final SimpleStringProperty roles;

        public UserData(String username, String name, String roles) {
            this.username = new SimpleStringProperty(username);
            this.name = new SimpleStringProperty(name);
            this.roles = new SimpleStringProperty(roles);
        }

        public StringProperty usernameProperty() { return username; }
        public StringProperty nameProperty() { return name; }
        public StringProperty rolesProperty() { return roles; }
    }
}
