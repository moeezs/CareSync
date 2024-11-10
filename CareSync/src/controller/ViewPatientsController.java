/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Controller for the ViewPatientsScreen
 */

package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

public class ViewPatientsController {

    @FXML
    private TableView<String> malePatientsTable;

    @FXML
    private TableView<String> femalePatientsTable;

    /**
     * Initializes the controller and sets up the table on the screen
     */
    @FXML
    private void initialize() {
    	
        List<String> malePatients = getPatientsFromDirectory("Patients/Male");
        List<String> femalePatients = getPatientsFromDirectory("Patients/Female");

        populateTable(malePatients, malePatientsTable);
        populateTable(femalePatients, femalePatientsTable);
        
        // Adds event listeners for when it clicks the name
        malePatientsTable.setOnMouseClicked(event -> {
			try {
				handleRowSelection(malePatientsTable);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
        
        femalePatientsTable.setOnMouseClicked(event -> {
			try {
				handleRowSelection(femalePatientsTable);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
    }

    /**
     * Checks if a name/row is clicked on in the patient tables
     *
     * @param table The TableView where the name is selected in
     * @throws IOException If it's not able to load the details of the patient
     */
    private void handleRowSelection(TableView<String> table) throws IOException {
        String selectedPatient = table.getSelectionModel().getSelectedItem();
        if (selectedPatient != null) {
            openPatientDetails(selectedPatient);
        }
    }

    /**
     * Retrieves patient names from the specified directory
     *
     * @param directoryPath The path of the directory containing patient directories
     * @return A list of patient names
     */
    private List<String> getPatientsFromDirectory(String directoryPath) {
        List<String> patients = new ArrayList<>();

        File directory = new File(directoryPath);
        
        // Checks directory and takes the name of every patients directory (their name)
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
     * Opens the details screen popup for the selected patient
     *
     * @param patientName The name of the selected patient
     * @throws IOException if an error occurs when loading the details
     */
    @FXML
    private void openPatientDetails(String patientName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/PatientDetailsScreen.fxml"));
        Parent root = loader.load();

        // Sets the data of the details FXML file with the data of the patient
        PatientDetailsController controller = loader.getController();
        controller.setPatientDetails(getPatientDetails(patientName));

        // Pop up window
        Stage stage = new Stage();
        stage.setTitle("Patient Details");
        stage.setScene(new Scene(root));
        stage.show();
        
    }

    /**
     * Retrieves patient details from the details.txt file for the specified patient
     *
     * @param patientName The name of the patient
     * @return Patient details as a string
     */
    private String getPatientDetails(String patientName) throws FileNotFoundException {
        String gender;
        
        if (isMale(patientName)) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        
        String detailsFilePath = "Patients/" + gender + "/" + patientName + "/details.txt";
        
        // Reads the file from the directory
        Scanner input = new Scanner(new FileReader(detailsFilePath));
        String details = "";

        while (input.hasNextLine()) {
        	// Adds all the text from the file in a String
            details += input.nextLine() + "\n";
        }

        return details;
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
