/**
 * 
 */
package webd4201.ClossG;

import java.text.DecimalFormat;

/**
 * @author Grayson Closs
 * @version 1.0
 * @since 2023-01-19
 */
public class Mark {
	
	public static final float MINIMUM_GPA = 0.0f;
	public static final float MAXIMUM_GPA = 5.0f;
	public static final DecimalFormat GPA = new DecimalFormat("0.00");
	/** A String that stores the acronym for the course : {@value}. */
	private String courseCode;
	/** A String that stores the full course name : {@value}. */
	private String courseName;
	/** An Integer that stores the overall percent grade : {@value}. */  
	private int result;
	/** A float that stores the GPA weight : {@value} */
	private float gpaWeighting;
	
	//Getters
	public String getCourseCode() {
		return courseCode;
	}
	public String getCourseName() {
		return courseName;
	}
	public int getResult() {
		return result;
	}
	public float getGpaWeighting() {
		return gpaWeighting;
	}
	
	//Setters
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public void setGpaWeighting(float gpaWeighting) {
		this.gpaWeighting = gpaWeighting;
	}
	
	//Constructors
	/**
	 * Default parameterized constructor
	 * {@link #Mark(String, String, int, float) Mark(String, String, integer, float)}
	 * @param courseCode  A method String used to set {@link #courseCode} 
	 * @param courseName  A method String used to set {@link #courseName}
	 * @param result  A method Integer used to set {@link #result}
	 * @param gpaWeighting  A method Float used to set {@link #gpaWeighting}
	 */
	public Mark(String courseCode, String courseName, int result, float gpaWeighting) {
		setCourseCode(courseCode);
		setCourseName(courseName);
		setResult(result);
		setGpaWeighting(gpaWeighting);
	}
	
	//Methods 
	/**
	 * @return  
	 * {@code getCourseCode() + "\t" + getCourseName() + "\t" + getResult() + "\t" + GPA.format(getGpaWeighting())};
	 */
	@Override
	public String toString() {
		return getCourseCode() + "\t" 
				+ getCourseName() + "\t" 
				+ getResult() + "\t" 
				+ GPA.format(getGpaWeighting());
	}
}
