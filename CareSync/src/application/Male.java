/**
 * @author Moeez Shaikh
 * @date 01/18/2021
 * Male class that inherits Patient Class
 */

package application;

public class Male extends Patient {
	
	private String hasProstateCancerHistory;
	
    public Male(String name, int age, String gender, int weightKG, String diagnosis, String hasProstateCancerHistory) {
        super(name, age, "Male", weightKG, diagnosis);
        this.hasProstateCancerHistory = hasProstateCancerHistory;
    }
    
    /**
	 * Gets the information if the patient has prostate cancer history
	 *
	 * @return The information if the patient has prostate cancer history
	 */
    public String hasProstateCancerHistory() {
        return hasProstateCancerHistory;
    }

 
    /**
     * Gets the information if the patient has prostate cancer history
     *
     * @param isPregnant The information if the patient has prostate cancer history
     */
    public void setHasProstateCancerHistory(String hasProstateCancerHistory) {
        this.hasProstateCancerHistory = hasProstateCancerHistory;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nHas Prostate Cancer History: " + hasProstateCancerHistory;
    }
    
}