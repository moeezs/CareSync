/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Patient Class
 */

package application;

public class Patient {
    private String name;
    private int age;
    private String gender;
    private int weightKG;
    private String diagnosis;


    // The constructor for the Patient class
    public Patient(String name, int age, String gender, int weightKG, String diagnosis) {
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.weightKG = weightKG;
		this.diagnosis = diagnosis;
	}
    
    /**
     * Gets the name of the patient
     *
     * @return The name of the patient
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the patient
     *
     * @param name The name to be set for the patient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the age of the patient
     *
     * @return The age of the patient
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the patient
     *
     * @param age The age to be set for the patient
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender of the patient
     *
     * @return The gender of the patient
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the patient
     *
     * @param gender The gender to be set for the patient
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the weight of the patient in kilograms
     *
     * @return The weight of the patient in kilograms
     */
    public int getWeightKG() {
        return weightKG;
    }

    /**
     * Sets the weight of the patient in kilograms
     *
     * @param weightKG The weight to be set for the patient
     */
    public void setWeightKG(int weightKG) {
        this.weightKG = weightKG;
    }

    /**
     * Gets the diagnosis of the patient
     *
     * @return The diagnosis of the patient
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis of the patient
     *
     * @param diagnosis The diagnosis to be set for the patient
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Represents the object as a formatted string with all the details
     *
     * @return A string representation of the patient's details
     */
    @Override
    public String toString() {
        return "Name: " + name +
                "\nAge: " + age +
                "\nGender: " + gender +
                "\nWeight: " + weightKG + "kg" +
                "\nDiagnosis: " + diagnosis;
    }
}