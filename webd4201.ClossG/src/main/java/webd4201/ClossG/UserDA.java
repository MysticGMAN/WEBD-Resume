package webd4201.ClossG;

import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.*;

import java.util.Date;

/**
 * @author Grayson Closs
 *
 */

@SuppressWarnings("unused")
public class UserDA {
	
	//static constants
	@SuppressWarnings("unused")
	//private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
	
	//static
	static Connection aConnection;
	static Statement aStatement;
	
	//static 
	static Vector<User> Users = new Vector<User>();
	static User aUser;
	
	//instance attributes
	static long id;
	static String password;
	static String usePass;
	static String firstName;
	static String lastName; 
	static String emailAddress;
	static java.sql.Date lastAccess;
	static java.sql.Date enrolDate;
	static boolean enabled;
	static char type;
	
	
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
	 * @param aUser
	 * @return
	 * @throws DuplicateException
	 * @throws SQLException 
	 */
	public static boolean create(User aUser) throws DuplicateException, SQLException
	{	
//		aConnection.setAutoCommit(false);
		boolean inserted = false; //insertion success flag
		// retrieve the customer attribute values
		id = aUser.getId();
		password = aUser.getPassword();
		firstName = aUser.getFirstName();
		lastName = aUser.getLastName();
		emailAddress = aUser.getEmailAddress();
		lastAccess = new java.sql.Date (aUser.getLastAccess().getTime());
		enrolDate = new java.sql.Date (aUser.getEnrolDate().getTime());
		enabled = aUser.getEnabled();
		type = aUser.getType();
		
		//insert string
		final String insertUser = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";//, id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		
		// see if this customer already exists in the database
		try {
			retrieve(id);
			throw (new DuplicateException("Problem with creating User record, ID: " + id +" already exists in the system."));
		}
		// if NotFoundException, add customer to database
		catch(NotFoundException e)
		{
			try {  
				PreparedStatement stmtUser = aConnection.prepareStatement(insertUser);
				stmtUser.setLong(1, id);
				stmtUser.setString(2, password);
				stmtUser.setString(3, firstName);
				stmtUser.setString(4, lastName);
				stmtUser.setString(5, emailAddress);
				stmtUser.setDate(6, lastAccess);
				stmtUser.setDate(7, enrolDate);
				stmtUser.setBoolean(8, enabled);
				stmtUser.setString(9, String.valueOf(type));
				
				// execute the SQL update statement
	    		inserted = (stmtUser.executeUpdate() == 1);
	    		//stmtStudent.execute();  		
			}
			catch (SQLException ee) { 
				System.out.println(ee);	
			}
		}
		return inserted;
	}
	/**
	 * 
	 * @param key
	 * @return
	 * @throws NotFoundException
	 */
	public static User retrieve(long key) throws NotFoundException { // retrieve Customer and Boat data
		aUser = null;
		
		final String selectUser = "SELECT CollegeId, Password, FirstName, LastName, EmailAddress, LastAccess, EnrolDate, Enabled, Type "
				+ "From users"
				+ " WHERE collegeid = ?";
		
	 	// execute the SQL query statement
		try
 		{
			PreparedStatement stmtUser = aConnection.prepareStatement(selectUser);
			stmtUser.setLong(1, key);
			
			ResultSet rs = stmtUser.executeQuery();
            // next method sets cursor & returns true if there is data
            boolean gotIt = rs.next();
            if (gotIt) {	// extract the data
            	id = rs.getInt("CollegeId");
            	password = rs.getString("password");
            	firstName = rs.getString("FirstName");
            	lastName = rs.getString("LastName");
            	emailAddress = rs.getString("EmailAddress");
            	lastAccess = rs.getDate("LastAccess");
            	enrolDate = rs.getDate("EnrolDate");
            	enabled = rs.getBoolean("Enabled");
            	type = rs.getString("Type").charAt(0);
            	
			
                        try{
                            aUser = new User(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
                            
                        }catch (InvalidUserDataException e) { 
                        	System.out.println("Record for " + id + " contains an invalid Id. User already exists.");
                        }
                    }else {
                    	throw (new NotFoundException("Problem retrieving User record, Id number " + key + " does not exist in the system."));
                    }
                    rs.close();
	   	}catch (SQLException e) { 
	   		System.out.println(e);
	   	}
                
		return aUser;
	}
	/**
	 * 
	 * @param aUser
	 * @return
	 * @throws NotFoundException
	 */
	public static int update(User aUser) throws NotFoundException
	{	
		int records = 0;  //records updated in method
		
		// retrieve the customer argument attribute values
		id = aUser.getId();
		password = aUser.getPassword();
		firstName = aUser.getFirstName();
		lastName = aUser.getLastName();
		emailAddress = aUser.getEmailAddress();
		lastAccess = new java.sql.Date (aUser.getLastAccess().getTime());
		enrolDate = new java.sql.Date (aUser.getEnrolDate().getTime());
		enabled = aUser.getEnabled();
		type = aUser.getType();
		

		// define the SQL query statement using the phone number key
		final String updateUser = "UPDATE users SET Password = ?, FirstName = ?, LastName = ?, EmailAddress = ?, LastAccess = ?, EnrolDate = ?, Enabled = ?, Type = ? Where CollegeId = ?";
		
				
		// see if this customer exists in the database
		// NotFoundException is thrown by find method
		try
		{
			PreparedStatement stmtUser = aConnection.prepareStatement(updateUser);
			stmtUser.setString(1, password);
			stmtUser.setString(2, firstName);
			stmtUser.setString(3, lastName);
			stmtUser.setString(4, emailAddress);
			stmtUser.setDate(5, lastAccess);
			stmtUser.setDate(6, enrolDate);
			stmtUser.setBoolean(7, enabled);
			stmtUser.setString(8, String.valueOf(type));
			stmtUser.setLong(9, id);
			
			
			
            User.retrieve(id);  //determine if there is a Customer recrod to be updated
                    // if found, execute the SQL update statement
            
            //TODO add two records in to count each record that is updated in every table updated.
            // Instead of overwriting previous record updates, as i am doing now
                    records = stmtUser.executeUpdate();
                    
		}catch(NotFoundException e)
		{
			throw new NotFoundException("User with ID " + id 
					+ " cannot be updated, does not exist in the system.");
		}catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	/**
	 * 
	 * @param aUser
	 * @return
	 * @throws NotFoundException
	 */
	public static int delete(User aUser) throws NotFoundException
	{	
		int records = 0;
		// retrieve the phone no (key)
		id = aUser.getId();
		// create the SQL delete statement
		
		String deleteUser = "DELETE FROM users WHERE CollegeId = ?";
		
		// see if this customer already exists in the database
		try {
			
			
			PreparedStatement stmtUser = aConnection.prepareStatement(deleteUser);
			stmtUser.setLong(1, id);
			User.retrieve(id);  //used to determine if record exists for the passed Customer
    		// if found, execute the SQL update statement
    		
    		records = stmtUser.executeUpdate();
		}catch (NotFoundException e) {
			
			throw new NotFoundException("User with ID " + id 
					+ " cannot be deleted, does not exist.");
			
		}catch (SQLException e) { 
			System.out.println(e);	
		}
		return records;
	}
	
//	public static User authenticate (long num, String pass) throws NotFoundException {
//		
//		User userAuth = null;
//		String err = "";
//		String authUser = "SELECT collegeid, password FROM users WHERE collegeid = ?";
//		
//		try {
//			usePass = Student.hashPassword(pass).toString();
//			PreparedStatement stmtId = aConnection.prepareStatement(authUser);
//			stmtId.setLong(1, num);
//			ResultSet rs = stmtId.executeQuery();
//            // next method sets cursor & returns true if there is data
//            if(rs.next()) {
//            	id = rs.getInt("collegeid");
//            	password = rs.getString("password").toString();
//            	if (num == id) {
//    				if (usePass.compareTo(password) == 0){
//    					userAuth = User.retrieve(num);
//    				}
//    				else {
//    					throw new SQLException("Pass no matchy");
//    				}
//    			}
//    			else {
//    				throw new SQLException();
//    			}
//            } else {
//            	throw new SQLException();
//            }
//
//			
//		} catch (SQLException e) {
//			
//			throw new NotFoundException("The user details provided do not exist in the database." + e.getMessage());
//		
//		} catch (InvalidPasswordException p) {
//			
//			System.out.println(p);
//			
//		} catch (Exception e) {
//			
//			System.out.println(e.getMessage());
//			
//		}
//		
//		return userAuth;
//	}
	
}


