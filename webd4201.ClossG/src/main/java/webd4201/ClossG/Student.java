package webd4201.ClossG;

import java.util.Date;
import java.util.Vector;
import java.sql.*;

/**
 * @author Grayson Closs
 * @version 1.0
 * @since 2023-01-20
 * 
 *
 */
public class Student extends User{

	//Constants
	public static final String DEFAULT_PROGRAM_CODE = "UNDC";
	public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
	public static final int DEFAULT_YEAR = 1;
	public static final String[] SUFFIX = {" ", "st", "nd", "rd", "th"};
	
	
	//Attributes
	private String programCode;
	private String programDescription;
	private int year;
	private Vector<Mark> marks;
	
	
	//Getters
	public String getProgramCode() {
		return programCode;
	}
	public String getProgramDescription() {
		return programDescription;
	}
	public int getYear() {
		return year;
	}
	public Vector<Mark> getMarks() {
		return marks;
	}
	
	//Setters
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setMarks(Vector<Mark> marks) {
		this.marks = marks;
	}
	
	//Constructors
	/**
	 * default param constructor
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @param marks
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year,
			Vector<Mark> marks) throws InvalidUserDataException {
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		try {
			setProgramCode(programCode);
			setProgramDescription(programDescription);
			setYear(year);
			setMarks(marks);
		}catch(Exception e) {
			throw new InvalidUserDataException(e.getMessage());
		}
	}
	/**
	 * param constructor that doesnt take the VECTOR of marks
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param programCode
	 * @param programDescription
	 * @param year
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public Student(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess,
			Date enrolDate, boolean enabled, char type, String programCode, String programDescription, int year) throws InvalidUserDataException {
		this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, null);
	}
	/**
	 * defualt constructor
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public Student() throws InvalidUserDataException {
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR); 
	}
	
	//Methods
	
	
	public static void initialize(Connection aConnection) {
		User.initialize(aConnection);
		StudentDA.initialize(aConnection);
	}
	
	public static void terminate() {
		StudentDA.terminate();
	}
	
	public static Student retrieve(long iden) throws NotFoundException {
		return StudentDA.retrieve(iden);
	}
	
	public static Student authenticate (long id, String pass) throws NotFoundException {
		return StudentDA.authenticate(id, pass);
	}
	
	public boolean create() throws DuplicateException, SQLException, InvalidUserDataException {
		return StudentDA.create(this);
	}
	
	public int update() throws NotFoundException {
		return StudentDA.update(this);
	}
	
	public int delete() throws NotFoundException {
		return StudentDA.delete(this);
	}
	
	
	/**
	 * overrides the toString method
	 * @override
	 * @return {$code Student Info for:\n"+ getFirstName() +" "+ getLastName() +"(" + getId() + ")\n"
		+ "Currently in " + getYear() + suffix +" year of \"" + getProgramDescription() + "\" (" + getProgramCode() + ")\n"
		+ "Enrolled On: " + DF.format(getEnrolDate())}
	 */
	@Override
	public String toString() {
		String suffix;
		if (getYear() > 4) {
			suffix = SUFFIX[3];
		}
		else{
			suffix = SUFFIX[getYear()];
		}
		return "Student Info for:\n"+ getFirstName() +" "+ getLastName() +"(" + getId() + ")\n"
		+ "Currently in " + getYear() + suffix +" year of \"" + getProgramDescription() + "\" (" + getProgramCode() + ")\nat " + COLLEGE_NAME
		+ "\nEnrolled On: " + DF.format(getEnrolDate());
	}
	
}
