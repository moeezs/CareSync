/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Controller for the HomeScreen
 */

package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeScreenController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    /**
     * Switches to the View Patients screen when the corresponding button is clicked
     *
     * @param event The action event triggering the switch
     * @throws IOException If an error occurs while loading the View Patients screen
     */
    @FXML
    private void switchToViewPatients(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/ViewPatientsScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the Add Patients screen when the corresponding button is clicked
     *
     * @param event The action event triggering the switch
     * @throws IOException If an error occurs while loading the Add Patients screen
     */
    @FXML
    private void switchToAddPatients(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/AddPatientsScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to the Remove Patients screen when the corresponding button is clicked
     *
     * @param event The action event triggering the switch
     * @throws IOException If an error occurs while loading the Remove Patients screen
     */
    @FXML
    private void switchToRemovePatients(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/RemovePatientsScreen.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
