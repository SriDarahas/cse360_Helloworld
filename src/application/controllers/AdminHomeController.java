package application.controllers;

import application.Main;
import application.models.User;
import application.utils.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class AdminHomeController {

    @FXML
    private Button inviteUserButton;
    @FXML
    private Button resetUserButton;
    @FXML
    private Button deleteUserButton;
    @FXML
    private Button listUsersButton;
    @FXML
    private Button manageRolesButton;
    @FXML
    private Button logoutButton;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    @FXML
    private void handleInviteUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/InviteUser.fxml"));
            Parent root = loader.load();

            InviteUserController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("Invite User");
            stage.setScene(new Scene(root));
            stage.show();

            // Close current window if necessary
            // ((Stage) inviteUserButton.getScene().getWindow()).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleResetUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/ResetUser.fxml"));
            Parent root = loader.load();

            ResetUserController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("Reset User Account");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDeleteUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/DeleteUser.fxml"));
            Parent root = loader.load();

            DeleteUserController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("Delete User Account");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleListUsers(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/ListUsers.fxml"));
            Parent root = loader.load();

            ListUsersController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("List User Accounts");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleManageRoles(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/ManageRoles.fxml"));
            Parent root = loader.load();

            ManageRolesController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("Manage User Roles");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            // Load Login.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) logoutButton.getScene().getWindow();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
