/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Female class that inherits Patient Class
 */

package application;

public class Female extends Patient {
	
	private String isPregnant;

	public Female(String name, int age, String gender, int weightKG, String diagnosis, String isPregnant) {
        super(name, age, "Female", weightKG, diagnosis);
        this.isPregnant = isPregnant;
    }
	
	/**
	 * Gets the pregnancy status of the patient
	 *
	 * @return The pregnancy status of the patient
	 */
    public String isPregnant() {
        return isPregnant;
    }

    /**
     * Sets the pregnancy status of the patient
     *
     * @param isPregnant The pregnancy status to be set for the patient
     */
    public void setPregnant(String isPregnant) {
        this.isPregnant = isPregnant;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nIs Pregnant: " + isPregnant;
    }
}

	