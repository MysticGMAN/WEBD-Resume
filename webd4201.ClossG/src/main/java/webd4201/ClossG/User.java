/**
 * 
 */
package webd4201.ClossG;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Grayson Closs
 *	@version 1.0
 *	@since 2023-01-18
 */
public class User implements CollegeInterface {
	
	//Class Constants
	public static final long DEFAULT_ID = 100123456;
	public static final String DEFAULT_PASSWORD = "password";
	public static final byte MINIMUM_PASSWORD_LENGTH = 8;
	public static final byte MAXIMUM_PASSWORD_LENGTH = 40;
	public static final String DEFAULT_FIRST_NAME = "John";
	public static final String DEFAULT_LAST_NAME = "Doe";
	public static final String DEFAULT_EMAIL_ADDRESS = DEFAULT_FIRST_NAME + "." + DEFAULT_LAST_NAME + "@dcmail.ca";
	public static final boolean DEFAULT_ENABLED_STATUS = true;
	public static final char DEFAULT_TYPE = 's';
	public static final byte ID_NUMBER_LENGTH = 9;
	public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM , Locale.CANADA); 
	
	//Instance Attributes 
	private long id;
	private String password;
	private String firstName;
	private String lastName; 
	private String emailAddress;
	private Date lastAccess;
	private Date enrolDate;
	private boolean enabled;
	private char type;
	
	//Getters
	public long getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public Date getLastAccess() {
		return lastAccess;
	}
	public Date getEnrolDate() {
		return enrolDate;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public char getType() {
		return type;
	}
	
	//Setters
	/**
	 * @param id
	 * @throws InvalidIdException
	 */
	public void setId(long id) throws InvalidIdException {
		if(verifyId(id)) {
			this.id = id;
		}
		else {
			throw new InvalidIdException("ID out of range. Must be 9 characters.");
		}
	}
	/**
	 * @param password
	 * @throws InvalidPasswordException
	 */
	public void setPassword(String password) throws InvalidPasswordException {
		password = hashPassword(password);
		if(verifyPassword(password)) {
			this.password = password;
		}
		else {
			throw new InvalidPasswordException("Password is out of range. Must be between "+MINIMUM_PASSWORD_LENGTH+" & "+MAXIMUM_PASSWORD_LENGTH+".");
		}
	}
	/**
	 * 
	 * @param firstName
	 * @throws InvalidNameException
	 */
	public void setFirstName(String firstName) throws InvalidNameException {
		if(verifyName(firstName)) {
			this.firstName = firstName;
		}
		else {
			throw new InvalidNameException("First Name invalid. Name can only contain characters.");
		}
	}
	/**
	 * 
	 * @param lastName
	 * @throws InvalidNameException
	 */
	public void setLastName(String lastName) throws InvalidNameException {
		if(verifyName(lastName)) {
			this.lastName = lastName;
		}
		else {
			throw new InvalidNameException("First Name invalid. Name can only contain characters.");
		}
	}
	public void setEmailAddress(String emailAddress) throws InvalidUserDataException {
		try {
		if(verifyEmail(emailAddress)){
			this.emailAddress = emailAddress;
		}
		}catch (InvalidUserDataException e) {
			throw new InvalidUserDataException("Email is Invalid" + e.getMessage());
		}
	}
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public void setType(char type) {
		this.type = type;
	}
	
	//Default Constructors/Constructors
	/**
	 * Default parameterized constructor
	 * @param id
	 * @param password
	 * @param firstName
	 * @param lastName
	 * @param emailAddress
	 * @param lastAccess
	 * @param enrolDate
	 * @param enabled
	 * @param type
	 * @throws InvalidUserDataException
	 */
	public User (long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type) throws InvalidUserDataException{
		
			try {
				setId(id);
				setPassword(password);
				setFirstName(firstName);
				setLastName(lastName);
				setEmailAddress(emailAddress);
				setLastAccess(lastAccess);
				setEnrolDate(enrolDate);
				setEnabled(enabled);
				setType(type);
			} catch (InvalidIdException e) {
				// TODO Auto-generated catch block
				//use getMessage instead of using e.toString
				throw new InvalidUserDataException(e.getMessage());
			}
			catch (InvalidPasswordException e) {
				throw new InvalidUserDataException(e.getMessage());
			}
			catch (InvalidNameException e) {
				throw new InvalidUserDataException(e.getMessage());
			}
			catch (Exception e)	{
				throw new InvalidUserDataException(e.getMessage());
			}

	}
	/**
	 * Default constructor
	 * @throws InvalidUserDataException
	 */
	public User() throws InvalidUserDataException{
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE);
	}
	
	//Methods
	public String getTypeForDisplay() {
		return this.getTypeForDisplay();
	}
	
	/**
	 * overrides toString
	 * @override
	 */
	@Override
	public String toString() {
		return "User Info for: " + getId() + "\n"
		+ "Name: " + getFirstName() + " " + getLastName() + " (" + getEmailAddress() + ")\n"
		+ "Created on: " + DF.format(getEnrolDate()) + "\n"
		+ "Last access: " + DF.format(getLastAccess());
	}
	
	public void dump() {
		System.out.println(toString());
	}
	/**
	 * verifys the ID
	 * @param testId
	 * @return
	 */
	public static boolean verifyId(long testId) {
		
		String str = Long.toString(testId);
		if (ID_NUMBER_LENGTH == str.length() && str != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * verifys password
	 * @param testPassword
	 * @return
	 */
	public static boolean verifyPassword(String testPassword) {
		if ((testPassword != null && !testPassword.isEmpty()) && 
				(testPassword.getBytes().length >= MINIMUM_PASSWORD_LENGTH && testPassword.getBytes().length <= MAXIMUM_PASSWORD_LENGTH)) {
			return true;
		}
		return false;
	}
	/**
	 * verifys Name
	 * @param name
	 * @return
	 */
	public static boolean verifyName(String name) {
		if (name != null && !(name.isEmpty())) {
			for (int i = 0; i < name.length(); i++) {
				if(Character.isDigit(name.charAt(i))) {
					return false;
				}
			}
		}
		else {
			return false;
		}
		return true;
	}
	
	public static boolean verifyEmail(String email) throws InvalidUserDataException {
		try {
		for (int i = 0; i <= email.length(); i++) {
			if (Character.isLetterOrDigit(email.charAt(i)) && email.charAt(i) == '@' ) {
				return true;
			}else {continue;}
//				if (i > 3 && email.charAt(i) == '@') {
//					if (i >= 5 && email.charAt(i) == '.') {
//						if (email.substring(i).length() <= 3) {
//							return true;
//						}else {
//							throw new Exception(" Sub String failed");
//						}
//					}else if (email.length() == i) {
//						throw new Exception(" @ if failed");
//					}
//					else {
//						continue;
//					}
//				}else if (email.length() == i) {
//					throw new Exception(" @ if failed");
//				}else {
//					continue;
//				}
//			}
		}
		}catch (Exception e) {
			return false;
//			throw new InvalidUserDataException(" No @");
//			throw new InvalidUserDataException(e.getMessage() + " " + e.getStackTrace()[1].getLineNumber());
		}
		return false;
	}
	
	public static String hashPassword(String pass) throws InvalidPasswordException {
		if (pass != null) {
			try {
						
				MessageDigest sHA1 = MessageDigest.getInstance("SHA-1");
				byte[] md = sHA1.digest(pass.getBytes());
				BigInteger fatMan = new BigInteger (1,md);
				return fatMan.toString(16);
				
			}catch (NoSuchAlgorithmException e) {
				
				throw new InvalidPasswordException("Hashing failed " + e.getMessage());
			}
		}
		return "";
	}
	
	public static void initialize(Connection aConnection) {
		UserDA.initialize(aConnection);
	}
	
	public static void terminate() {
		UserDA.terminate();
	}
	
	public static User retrieve(long iden) throws NotFoundException {
		return UserDA.retrieve(iden);
	}
	
//	public static User authenticate (long id, String pass) throws NotFoundException {
//		return UserDA.authenticate(id, pass);
//	}
	
	public boolean create() throws DuplicateException, SQLException, InvalidUserDataException {
		return UserDA.create(this);
	}
	
	public int update() throws NotFoundException {
		return UserDA.update(this);
	}
	
	public int delete() throws NotFoundException {
		return UserDA.delete(this);
	}
}
