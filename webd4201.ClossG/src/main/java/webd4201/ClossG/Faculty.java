package webd4201.ClossG;


import java.sql.Connection;
import java.util.Date;

/**
 * @author Grayson Closs
 * @version 2.0
 * @since 2023-02-15
 * This file is to handle the faculty part of the users registered
 * with this java program
 */
public class Faculty extends User{
	
	//Class Constants
	/**
	 * @param DEFAULT_SCHOOL_CODE A constant that holds "SET"
	 * @param DEFAULT_SCHOOL_DESCRIPTION A constant that holds "School of Science, Engineering & Information Technology"
	 * @param DEFAULT_OFFICE A constant that holds "H-140"
	 * @param DEFAULT_PHONE_BOOK A constant that holds "1234"  
	 */
	public static final String DEFAULT_SCHOOL_CODE = "SET";
	public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Science, Engineering & Information Technology";
	public static final String DEFAULT_OFFICE = "H-140";
	public static final int DEFAULT_PHONE_EXTENSION = 1234;
	
	//Attributes
	/**
	 * @param schoolCode  Stores acronym for the department section of the school
	 * @param schoolDescription  Stores the full name of the schools department
	 * @param office  Stores the office room number
	 * @param extension  Stores the phone extension 
	 */
	private String schoolCode;
	private String schoolDescription;
	private String office;
	private int extension;
	
	//Getters
	/**
	 * @return The schoolCode String
	 */
	public String getSchoolCode() {
		return schoolCode;
	}
	/**
	 * @return The schoolDescription String
	 */
	public String getSchoolDescription() {
		return schoolDescription;
	}
	/**
	 * @return The office String 
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @return The extension String
	 */
	public int getExtension() {
		return extension;
	}
	
	//Setters
	/**
	 * Stores the acronym for the department section of the school
	 * @param schoolCode 
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	/**
	 * Stores the full school department name
	 * @param schoolDescription   
	 */
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
	}
	/**
	 * Stores the room number for the office of the department
	 * @param office
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * Stores the phone extension
	 * @param extension
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}
	
	//Default Constructors
	/**
	 * defualt parameterized constructor 
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @param schoolCode
	 * @param schoolDescription
	 * @param office
	 * @param extension
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public Faculty(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type, String schoolCode, String schoolDescription, String office, int extension) throws InvalidUserDataException {
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		setSchoolCode(schoolCode);
		setSchoolDescription(schoolDescription);
		setOffice(office);
		setExtension(extension);
	}
	/**
	 * default constructor
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 */
	public Faculty() throws InvalidUserDataException {
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE, DEFAULT_SCHOOL_CODE, DEFAULT_SCHOOL_DESCRIPTION, DEFAULT_OFFICE, DEFAULT_PHONE_EXTENSION);
	}
	
	//Methods
	/**
	 * overrides the abstract method declared in the user class
	 * @returns a string literal Faculty
	 */
	public String getTypeForDisplay() {
		return "Faculty";
	}
	
	/**
	 * To initialize the DB connection as the faculty object
	 * @param aConnection
	 */
	public static void initialize(Connection aConnection) {
		FacultyDA.initialize(aConnection);
	}
	/**
	 * to terminate the DB connection as a faculty object
	 */
	public static void terminate() {
		FacultyDA.terminate();
	}
	/**
	 * To retrieve the faculty data from the database as a faculty object
	 * @param iden
	 * @return
	 * @throws NotFoundException
	 */
	public static Faculty retrieve(long iden) throws NotFoundException {
		return FacultyDA.retrieve(iden);
	}
	/**
	 * To create a faculty user in the DB as a faculty object
	 * @return
	 * @throws DuplicateException
	 */
	public boolean create() throws DuplicateException {
		return FacultyDA.create(this);
	}
	/**
	 * To update the database faculty data as a faculty object
	 * @return FacultyDA.update(this)
	 * @throws NotFoundException
	 */
	public int update() throws NotFoundException {
		return FacultyDA.update(this);
	}
	/**
	 * To delete the faculty data as the faculty object
	 * @return FacultyDA.delete(this)
	 * @throws NotFoundException
	 */
	public int delete() throws NotFoundException {
		return FacultyDA.delete(this);
	}
	
	/**
	 * overrides the to java object toString and calls the toString method from
	 * the user class and stores that in the String var output.
	 * @param output stores the return from the user toString.
	 * @override
	 */
	@Override
	public String toString() {
		String output = super.toString();
		output = output.replaceAll("User", "Faculty");
		return output + "\n" + getSchoolDescription() + "\n"
				+ "Office: " + getOffice() + "\n"
				+ "(905)-721-2000\tx" + getExtension(); 
	}
}
