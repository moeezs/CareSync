/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Controller for the AddPatientsScreen
 */

package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.Male;
import application.Female;
import application.Patient;

public class AddPatientsController {
	
	private Stage stage;
    private Scene scene;
    private Parent root;
    
    // FXML components

    @FXML
    private TextField nameField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField diagnosisField;


    @FXML
    private ChoiceBox<String> genderChoiceBox;

    @FXML
    private ChoiceBox<String> additionalQuestionChoiceBox;
    
    @FXML
    private Label genderQuestionLabel;
    
    @FXML
    private Button addPatientButton;
    
    /**
     * Adds the gender and additional question choice boxes options
     * Sets the initial UI state and adds a listener for the gender choice box
     */
    @FXML
    private void initialize() {
        // Adds the gender choice box options
        ObservableList<String> genderOptions = FXCollections.observableArrayList("Male", "Female");
        genderChoiceBox.setItems(genderOptions);

        // Adds the additional question, based on the gender, choice box options
        ObservableList<String> additionalQuestionOptions = FXCollections.observableArrayList("Yes", "No");
        additionalQuestionChoiceBox.setItems(additionalQuestionOptions);

        // Set initial state
        setInitialUIState();

        // Add listener for gender choice box
        genderChoiceBox.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> handleGenderSelection(newValue)
        );
    }

    /**
     * Sets the initial state of the UI, disabling and hiding certain components
     */
    private void setInitialUIState() {
    	// Empties the question field and disables the buttons
    	genderQuestionLabel.setText("");
        additionalQuestionChoiceBox.setDisable(true);
        addPatientButton.setDisable(true);
    }

    /**
     * Puts the specific question on the label based on the selected gender and enables the selection for that question
     *
     * @param selectedGender The selected gender from the gender choice box
     */
    private void handleGenderSelection(String selectedGender) {
        if (selectedGender != null) {
            // Enable and set question based on selected gender
        	if (selectedGender == "Male") {
        		genderQuestionLabel.setText("Has Prostate Cancer History?");
        	} else {
        		genderQuestionLabel.setText("Is Pregnant?");
        	}
            additionalQuestionChoiceBox.setDisable(false);
            addPatientButton.setDisable(false);
        } else {
            // Disable components if no gender is selected
            setInitialUIState();
        }
    }
    
    /**
     * Adds a new patient to the local database based on the entered information and saves the details to a file
     *
     * @param event The action event triggered by the "Add Patient" button
     * @throws IOException If an error occurs while saving the patient details to a file
     */
    @FXML
    private void addPatient(ActionEvent event) throws IOException {
        try {
        	// Gets all the variables through the fields
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            int weightKG = Integer.parseInt(weightField.getText());
            String diagnosis = diagnosisField.getText();
            String additionalQuestion = additionalQuestionChoiceBox.getValue();
           
            Patient newPatient;
            if (genderChoiceBox.getValue().equalsIgnoreCase("Male")) {
                newPatient = new Male(name, age, "Male", weightKG, diagnosis, additionalQuestion);
            } else {
                newPatient = new Female(name, age, "Female", weightKG, diagnosis, additionalQuestion);
            }

            savePatientToFile(newPatient);
            showSuccessAlert("Patient added successfully!");
            switchToHomeScreen(event);
        } catch (NumberFormatException e) {
        	System.out.println("Please enter valid numeric values for age and weight.");
        } catch (IllegalArgumentException e) {
        	System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the details of the provided patient to a file.
     *
     * @param patient The patient whose details need to be saved.
     * @throws IOException If an I/O error occurs while saving the patient details to a file.
     */
	private void savePatientToFile(Patient patient) throws IOException {
        String genderDirectory;
        if (patient.getGender().equalsIgnoreCase("male")) {
        	genderDirectory = "Male";
        } else {
        	genderDirectory = "Female";
        }
        
        
        String patientDirectory = "Patients/" + genderDirectory + "/" + patient.getName();
        String detailsFilePath = patientDirectory + "/details.txt";

        // Creates new directory with that patient
        new File(patientDirectory).mkdirs();

        FileWriter writer = new FileWriter(detailsFilePath);
    	writer.write(patient.toString());
    	writer.close();
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
