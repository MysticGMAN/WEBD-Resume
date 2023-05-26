package webd4201.ClossG;
/**
 * @author Grayson Closs
 * @version 1.0
 * @since 2023-01-18
 */
public interface CollegeInterface {
	
	/**
	 * @param COLLEGE_NAME String constant {@value} .  
	 */
	public final String COLLEGE_NAME = "Durham College";
	/**
	 * @param PHONE_NUMBER Stores a constant String {@value} .
	 */
	public final String PHONE_NUMBER = "(905)-721-2000";
	
	/**
	 * Abstract String method getTypeDisplay().
	 * @return Returns a String literal {@value}
	 */
	public abstract String getTypeForDisplay();
	
}
