package webd4201.ClossG;

import java.text.SimpleDateFormat;
import java.util.Vector;

import org.postgresql.util.PSQLException;

import java.sql.*;

import java.util.Date;

/**
 * @author Grayson Closs
 *
 */

public class StudentDA {
	
	//static constants
	@SuppressWarnings("unused")
	private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
	
	//static
	static Connection aConnection;
	static Statement aStatement;
	
	//static 
	static Vector<Student> Students = new Vector<Student>();
	static Student aStudent;
	static User aUser;
	
	//instance attributes
	static long id;
	static String password;
	static String stuPass;
	static String firstName;
	static String lastName; 
	static String emailAddress;
	static java.sql.Date lastAccess;
	static java.sql.Date enrolDate;
	static boolean enabled;
	static char type;
	static String programCode;
	static String programDescription;
	static int year;
	
	
	
	// establish the database connection
	/**
	 * 
	 * @param conn
	 */
	public static void initialize(Connection conn)
	{
            try {
                aConnection = conn;
                aStatement = aConnection.createStatement();
                
                
            }
            catch (SQLException e) { 
            	System.out.println(e);	
            }
	}
	/**
	 * 
	 */
	public static void terminate()
	{
            try{ 	// close the statement
                aStatement.close();
            }
            catch (SQLException e){
            	System.out.println(e);	
            }
	}
	
	/**
	 * 
	 * @param aStudent
	 * @return
	 * @throws DuplicateException
	 * @throws SQLException 
	 * @throws InvalidUserDataException 
	 */
	public static boolean create(Student aStudent) throws DuplicateException, SQLException, InvalidUserDataException
	{	
		aConnection.setAutoCommit(false);
		boolean stuInserted = false; //insertion success flag
		boolean userInserted  = false;
		// retrieve the customer attribute values
		id = aStudent.getId();
		password = aStudent.getPassword();
		firstName = aStudent.getFirstName();
		lastName = aStudent.getLastName();
		emailAddress = aStudent.getEmailAddress();
		lastAccess = new java.sql.Date (aStudent.getLastAccess().getTime());
		enrolDate = new java.sql.Date (aStudent.getEnrolDate().getTime());
		enabled = aStudent.getEnabled();
		type = aStudent.getType();
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();
		
		//insert string
		//User user = new User(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		//userInserted = user.create();
		final String insertStudent = "INSERT INTO students (CollegeId, ProgramCode, ProgramDescription, CurrentYear) VALUES(?, ?, ?, ?)";//, CollegeId, programCode, programDescription, year);
		
		// see if this customer already exists in the database
		try {
			retrieve(id);
			throw (new DuplicateException("Problem with creating Student record, ID: " + id +" already exists in the system."));
		}
		// if NotFoundException, add customer to database
		catch(NotFoundException e)
		{
			try {
				userInserted = UserDA.create(aStudent);
				PreparedStatement stmtStudent = aConnection.prepareStatement(insertStudent);
				stmtStudent.setLong(1, id);
				stmtStudent.setString(2, programCode);
				stmtStudent.setString(3, programDescription);
				stmtStudent.setInt(4, year);
				// execute the SQL update statement
				stuInserted = (stmtStudent.executeUpdate() == 1);
			}
			catch (SQLException ee) { 
				System.out.println(ee);	
			}
		}
		if (userInserted && stuInserted) {
			aConnection.commit();
		}else {
			System.out.println("Create failed rollback initiated");
			aConnection.rollback();
		}
		aConnection.setAutoCommit(true);
		return stuInserted && userInserted;
	}
	/**
	 * 
	 * @param key
	 * @return
	 * @throws NotFoundException
	 */
	public static Student retrieve(long key) throws NotFoundException { // retrieve Customer and Boat data
		aStudent = null;
		
		final String selectStudent = "SELECT CollegeId, ProgramCode, ProgramDescription, CurrentYear FROM students WHERE CollegeId = ?";
		
		
	 	// execute the SQL query statement
		try
 		{
			PreparedStatement stmtStudent = aConnection.prepareStatement(selectStudent);
			stmtStudent.setLong(1, key);
			
			ResultSet rs = stmtStudent.executeQuery();
            // next method sets cursor & returns true if there is data
            boolean gotIt = rs.next();
            if (gotIt) {	// extract the data
            	id = rs.getInt("CollegeId");
            	
            	aUser = User.retrieve(key);
            	password = aUser.getPassword();
            	firstName = aUser.getFirstName();
            	lastName = aUser.getLastName();
            	emailAddress = aUser.getEmailAddress();
            	lastAccess = (java.sql.Date) aUser.getLastAccess();
            	enrolDate = (java.sql.Date)aUser.getEnrolDate();
            	enabled = aUser.getEnabled();
            	type = aUser.getType();
            	programCode = rs.getString("ProgramCode");
            	programDescription = rs.getString("ProgramDescription");
            	year = rs.getInt("CurrentYear");
			
                        try{
                            aStudent = new Student(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year);
                            
                        }catch (InvalidUserDataException e) { 
                        	System.out.println("Record for " + id + " contains an invalid Id. Student already exists.");
                        }
                    }else {
                    	throw (new NotFoundException("Problem retrieving Student record, Id number " + key + " does not exist in the system."));
                    }
                    rs.close();
	   	}catch (SQLException e) { 
	   		e.printStackTrace();
	   		System.out.println(e);
	   	}
                
		return aStudent;
	}
	/**
	 * 
	 * @param aStudent
	 * @return
	 * @throws NotFoundException
	 */
	public static int update(Student aStudent) throws NotFoundException
	{	
		int records = 0;  //records updated in method
		
		// retrieve the customer argument attribute values
		id = aStudent.getId();
		
		programCode = aStudent.getProgramCode();
		programDescription = aStudent.getProgramDescription();
		year = aStudent.getYear();

		// define the SQL query statement using the phone number key
		
		final String updateStudent = "UPDATE students SET CollegeId = ?, ProgramCode = ?, ProgramDescription = ?, CurrentYear = ? WHERE CollegeId = ?";
				
		// see if this customer exists in the database
		// NotFoundException is thrown by find method
		try
		{
			
			PreparedStatement stmtStudent = aConnection.prepareStatement(updateStudent);
			stmtStudent.setLong(1, id);
			stmtStudent.setString(2, programCode);
			stmtStudent.setString(3, programDescription);
			stmtStudent.setInt(4, year);
			stmtStudent.setLong(5, id);
            Student.retrieve(id);  //determine if there is a Customer recrod to be updated
                    // if found, execute the SQL update statement
            
          //TODO add two records in to count each record that is updated in every table updated.
          // Instead of overwriting previous record updates, as i am doing now
                    
                    records = stmtStudent.executeUpdate();
		}catch(NotFoundException e)
		{
			throw new NotFoundException("Student with ID " + id 
					+ " cannot be updated, does not exist in the system.");
		}catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	/**
	 * 
	 * @param aStudent
	 * @return
	 * @throws NotFoundException
	 */
	public static int delete(Student aStudent) throws NotFoundException
	{	
		int records = 0;
		// retrieve the phone no (key)
		id = aStudent.getId();
		// create the SQL delete statement
		String deleteStudent = "DELETE FROM students WHERE CollegeId = ?";
		
		
		// see if this customer already exists in the database
		try {
			
			PreparedStatement stmtStudent = aConnection.prepareStatement(deleteStudent);
			stmtStudent.setLong(1, id);
			
			Student.retrieve(id);  //used to determine if record exists for the passed Customer
    		// if found, execute the SQL update statement
    		records = stmtStudent.executeUpdate();
    		
		}catch (NotFoundException e) {
			
			throw new NotFoundException("Student with ID " + id 
					+ " cannot be deleted, does not exist.");
			
		}catch (SQLException e) { 
			System.out.println(e);	
		}
		return records;
	}
	
	public static Student authenticate (long num, String pass) throws NotFoundException {
		
		Student stuAuth = null;
		String err = "";
		String authStudent = "SELECT collegeid, password FROM users WHERE collegeid = ?";
		
		try {
			stuPass = Student.hashPassword(pass).toString();
			PreparedStatement stmtId = aConnection.prepareStatement(authStudent);
			stmtId.setLong(1, num);
			ResultSet rs = stmtId.executeQuery();
            // next method sets cursor & returns true if there is data
            if(rs.next()) {
            	id = rs.getInt("Collegeid");
            	password = rs.getString("password").toString();
            	if (num == id) {
    				if (stuPass.compareTo(password) == 0){
    					stuAuth = Student.retrieve(num);
    				}
    				else {
    					System.out.println("Pass no matchy");
    					throw new SQLException("Pass no matchy");
    				}
    			}
    			else {
    				System.out.println("ids didnt match");
    				throw new SQLException("ids didnt match");
    			}
            } else {
            	System.out.println("rs.next failed");
            	throw new SQLException("rs.next failed");
            }

		} catch(PSQLException e) {
			
			e.printStackTrace();
		}
		 catch (SQLException e) {
			
			throw new NotFoundException("The user details provided do not exist in the database." + e.getMessage());
		
		
		 }catch (InvalidPasswordException p) {
			
			System.out.println(p);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			
		}
		
		return stuAuth;
	}
	
}


