package application.controllers;

import application.models.InvitationCode;
import application.models.Role;
import application.models.User;
import application.utils.CodeGenerator;
import application.utils.DataManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InviteUserController {

    @FXML
    private ListView<Role> rolesListView;
    @FXML
    private Button generateCodeButton;
    @FXML
    private Label codeLabel;
    @FXML
    private Label errorLabel;

    private User currentUser;

    public void setCurrentUser(User user) {
        this.currentUser = user;
        rolesListView.setItems(FXCollections.observableArrayList(Role.values()));
        rolesListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @FXML
    private void handleGenerateCode(ActionEvent event) {
        Set<Role> selectedRoles = new HashSet<>(rolesListView.getSelectionModel().getSelectedItems());

        if (selectedRoles.isEmpty()) {
            errorLabel.setText("Please select at least one role.");
            return;
        }

        String code = CodeGenerator.generateCode();
        InvitationCode invite = new InvitationCode(code, selectedRoles);

        Map<String, InvitationCode> invites = DataManager.getInvites();
        invites.put(code, invite);
        DataManager.saveInviteData();

        codeLabel.setText("Invitation Code: " + code);
        errorLabel.setText("");
    }
}
