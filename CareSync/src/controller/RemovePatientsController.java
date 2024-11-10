/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Controller for the RemovePatientsScreen
 */

package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class RemovePatientsController {

    @FXML
    private TableView<String> malePatientsTable;

    @FXML
    private TableView<String> femalePatientsTable;

    /**
     * Initializes the controller and sets up the table on the screen
     */
    @FXML
    private void initialize() {
        loadPatients();
        malePatientsTable.setOnMouseClicked(event -> handleRowSelection(malePatientsTable));
        femalePatientsTable.setOnMouseClicked(event -> handleRowSelection(femalePatientsTable));
    }

    /**
     * Checks if a name/row is clicked on in the patient tables
     *
     * @param table The TableView where the name is selected in
     * @throws IOException If it's not able to load the details of the patient
     */
    private void handleRowSelection(TableView<String> table) {
        String selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
        	removePatient(selectedPatient, isMale(selectedPatient));
        }
    }

    /**
     * Loads patient names from the specified directory and populates the tables.
     */
    private void loadPatients() {
        List<String> malePatients = getPatientsFromDirectory("Patients/Male");
        List<String> femalePatients = getPatientsFromDirectory("Patients/Female");

        populateTable(malePatients, malePatientsTable);
        populateTable(femalePatients, femalePatientsTable);
    }

    /**
     * Retrieves patient names from the specified directory
     *
     * @param directoryPath The path of the directory containing patient directories
     * @return A list of patient names
     */
    private List<String> getPatientsFromDirectory(String directoryPath) {
        List<String> patients = new ArrayList<>();

        // Checks directory and takes the name of every patients directory (their name)
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            for (File patientDirectory : directory.listFiles()) {
                if (patientDirectory.isDirectory()) {
                    patients.add(patientDirectory.getName());
                }
            }
        }

        return patients;
    }

    /**
     * Populates the given table with patient names
     *
     * @param patients A list of patient names
     * @param table    The TableView to populate the names with
     */
    private void populateTable(List<String> patients, TableView<String> table) {
        TableColumn<String, String> nameColumn = new TableColumn<>("Patient's names");
        nameColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()));

        // Allows the column to take up the entire width of the table
        nameColumn.prefWidthProperty().bind(table.widthProperty());
        
        // Adds the patients on the tables
        table.getColumns().setAll(nameColumn);
        table.getItems().setAll(patients);
    }

    /**
     * Removes a patient's directory and all its contents based on the patient's name and gender directory
     *
     * @param patientName The name of the patient to be removed
     * @param isMale True if the patient is male, false if female
     */
    private void removePatient(String patientName, boolean isMale) {
    	String genderDirectory;
        
        if (isMale) {
        	genderDirectory = "Male";
        } else {
        	genderDirectory = "Female";
        }
        File patientDirectory = new File("Patients/" + genderDirectory + "/" + patientName);

        if (patientDirectory.exists() && patientDirectory.isDirectory()) {
            if (deleteDirectory(patientDirectory)) {
                showSuccessAlert("Patient removed successfully!");
                loadPatients(); // Refresh the table after removal
            } else {
            	System.out.println("Can't delete directory");
            }
        }
    }
    
    /**
     * Deletes a directory and all its contents
     *
     * @param directory The directory to be deleted
     * @return True if the directory is successfully deleted, false otherwise
     */
    private boolean deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
        	// Deletes every file within the directory
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directory.delete();
    }

    /**
     * Displays a success alert with the given message.
     *
     * @param message The message to be displayed in the success alert.
     */
    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Checks if the patient is male by checking if the directory exists
     *
     * @param patientName The name of the patient
     * @return True if the patient is male, false if female
     */
    private boolean isMale(String patientName) {
    	// Checks if a patient is a male by putting it's name in the directory and seeing if it exists
        File maleDirectory = new File("Patients/Male/" + patientName);
        return maleDirectory.exists() && maleDirectory.isDirectory();
    }

    /**
     * Switches to the home screen
     *
     * @param event The action event triggering the switch
     * @throws IOException If an error occurs while loading the home screen
     */
    @FXML
    private void switchToHomeScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/HomeScreen.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
}
