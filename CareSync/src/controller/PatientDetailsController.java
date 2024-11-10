/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Controller for the PatientDetailsScreen (Small popup window)
 */

package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PatientDetailsController {

    @FXML
    private Label detailsLabel;

    /**
     * Sets the patient details in the window by updating the label with the provided information
     *
     * @param details The details of the patient to be displayed
     */
    public void setPatientDetails(String details) {
        detailsLabel.setText(details);
    }
}
