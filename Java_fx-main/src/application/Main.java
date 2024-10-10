package application;

import application.utils.DataManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            DataManager.loadUserData();

            // Check if users exist
            if (DataManager.getUsers().isEmpty()) {
                // Load Signup.fxml for Admin
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Signup.fxml"));
                Scene scene = new Scene(loader.load());
                primaryStage.setScene(scene);
                primaryStage.setTitle("Create Admin Account");
                primaryStage.show();
            } else {
                // Load Login.fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxml/Login.fxml"));
                Scene scene = new Scene(loader.load());
                primaryStage.setScene(scene);
                primaryStage.setTitle("Login");
                primaryStage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
