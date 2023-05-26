package webd4201.ClossG;

import java.text.SimpleDateFormat;
import java.util.Vector;
import java.sql.*;
/**
 * @author Grayson Closs
 * @version 1.0
 * @since 2023-02-15
 * This is to establish a connection between the the faculty object
 * and the database to help with storing the faculty data in the DB
 *
 */
public class FacultyDA {

	//static constants
		@SuppressWarnings("unused")
		private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyyy-MM-dd");
		
		//static
		static Connection aConnection;
		static Statement aStatement;
		
		//static 
		//static Vector<Student> Students = new Vector<Student>();
		static Faculty aFaculty;
		
		//instance attributes
		static long id;
		static String password;
		static String firstName;
		static String lastName; 
		static String emailAddress;
		static java.sql.Date lastAccess;
		static java.sql.Date enrolDate;
		static boolean enabled;
		static char type;
		static String schoolCode;
		static String schoolDescription;
		static String office;
		static int extension;
		
		
		
		
		/**
		 * establish the database connection to store new faculty data in the DB
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
		 * Closes the connection between the java program and the DB 
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
		 * This creates a faculty entry in the DB
		 * @param aFaculty
		 * @return aFaculty
		 * @throws DuplicateException
		 */
		public static boolean create(Faculty aFaculty) throws DuplicateException
		{	
			boolean inserted = false; //insertion success flag
			// retrieve the customer attribute values
			id = aFaculty.getId();
			password = aFaculty.getPassword();
			firstName = aFaculty.getFirstName();
			lastName = aFaculty.getLastName();
			emailAddress = aFaculty.getEmailAddress();
			lastAccess = new java.sql.Date (aFaculty.getLastAccess().getTime());
			enrolDate = new java.sql.Date (aFaculty.getEnrolDate().getTime());
			enabled = aFaculty.getEnabled();
			type = aFaculty.getType();
			schoolCode = aFaculty.getSchoolCode();
			schoolDescription = aFaculty.getSchoolDescription();
			office = aFaculty.getOffice();
			extension = aFaculty.getExtension();
			
		
			
			//insert string
			final String insertUser = "INSERT INTO users VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";//, id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
			final String insertFaculty = "INSERT INTO faculty (CollegeId, SchoolCode, SchoolDescription, Office, Extension) VALUES(?, ?, ?, ?, ?)";//, CollegeId, programCode, programDescription, year);
			
			// see if this customer already exists in the database
			try {
				retrieve(id);
				throw (new DuplicateException("Problem with creating Faculty record, ID: " + id +" already exists in the system."));
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
					PreparedStatement stmtFaculty = aConnection.prepareStatement(insertFaculty);
					stmtFaculty.setLong(1, id);
					stmtFaculty.setString(2, schoolCode);
					stmtFaculty.setString(3, schoolDescription);
					stmtFaculty.setString(4, office);
					stmtFaculty.setInt(5, extension);
					// execute the SQL update statement
		    		stmtUser.execute();
		    		stmtFaculty.execute();
		    		inserted = true;
		    			   		
				}
				catch (SQLException ee) { 
					System.out.println(ee);	
				}
			}
			return inserted;
		}
		/**
		 * To query the database for existing faculty data
		 * @param key
		 * @return aFaculty
		 * @throws NotFoundException
		 */
		public static Faculty retrieve(long key) throws NotFoundException { // retrieve Customer and Boat data
			aFaculty = null;
			
			final String selectFaculty = "SELECT u.CollegeId, u.Password, u.FirstName, u.LastName, u.EmailAddress, u.LastAccess, u.EnrolDate, u.Enabled, u.Type, "
					+ "f.SchoolCode, f.schoolDescription, f.office, f.extension "
					+ "From users u, faculty f "
					+ "WHERE u.CollegeId = f.CollegeId AND u.CollegeId = ?";
			
		 	// execute the SQL query statement
			try
	 		{
				PreparedStatement stmtFaculty = aConnection.prepareStatement(selectFaculty);
				stmtFaculty.setLong(1, key);
				
				ResultSet rs = stmtFaculty.executeQuery();
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
	            	schoolCode = rs.getString("SchoolCode");
	            	schoolDescription = rs.getString("SchoolDescription");
	            	office = rs.getString("office");
	            	extension = rs.getInt("extension");
				
	                        try{
	                            aFaculty = new Faculty(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, schoolCode, schoolDescription, office, extension);
	                            
	                        }catch (InvalidUserDataException e) { 
	                        	System.out.println("Record for " + id + " contains an invalid Id. Faculty already exists.");
	                        }
	                    }else {
	                    	throw (new NotFoundException("Problem retrieving Faculty record, Id number " + key + " does not exist in the system."));
	                    }
	                    rs.close();
		   	}catch (SQLException e) { 
		   		System.out.println(e);
		   	}
	                
			return aFaculty;
		}
		/**
		 * To update existing faculty data in the DB 
		 * @param aFaculty
		 * @return
		 * @throws NotFoundException
		 */
		public static int update(Faculty aFaculty) throws NotFoundException
		{	
			int records = 0;  //records updated in method
			
			// retrieve the customer argument attribute values
			id = aFaculty.getId();
			password = aFaculty.getPassword();
			firstName = aFaculty.getFirstName();
			lastName = aFaculty.getLastName();
			emailAddress = aFaculty.getEmailAddress();
			lastAccess = new java.sql.Date (aFaculty.getLastAccess().getTime());
			enrolDate = new java.sql.Date (aFaculty.getEnrolDate().getTime());
			enabled = aFaculty.getEnabled();
			type = aFaculty.getType();
			schoolCode = aFaculty.getSchoolCode();
			schoolDescription = aFaculty.getSchoolDescription();
			office = aFaculty.getOffice();
			extension = aFaculty.getExtension();

			// define the SQL query statement using the phone number key
			final String updateUser = "UPDATE users SET Password   = ?, FirstName   = ?, LastName   = ?, EmailAddress   = ?, LastAccess   = ?, EnrolDate  = ?, Enabled   = ?, Type   = ? Where CollegeId   = ?";
			final String updateFaculty = "UPDATE faculty SET SchoolCode   = ?, SchoolDescription   = ?, office   = ?, extension   = ? WHERE CollegeId   = ?";
					
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
				PreparedStatement stmtFaculty = aConnection.prepareStatement(updateFaculty);
				stmtFaculty.setString(1, schoolCode);
				stmtFaculty.setString(2, schoolDescription);
				stmtFaculty.setString(3, office);
				stmtFaculty.setInt(4, extension);
				stmtFaculty.setLong(5, id);
	            Faculty.retrieve(id);  //determine if there is a Customer recrod to be updated
	                    // if found, execute the SQL update statement
	            
	          //TODO add two records in to count each record that is updated in every table updated.
	            // Instead of overwriting previous record updates, as i am doing now
	                    records = stmtUser.executeUpdate();
	                    records = stmtFaculty.executeUpdate();
			}catch(NotFoundException e)
			{
				throw new NotFoundException("Faculty with ID " + id 
						+ " cannot be updated, does not exist in the system.");
			}catch (SQLException e) {
				System.out.println(e);
			}
			return records;
		}
		/**
		 * This deletes the faculty data from the DB table
		 * @param aFaculty
		 * @return
		 * @throws NotFoundException
		 */
		public static int delete(Faculty aFaculty) throws NotFoundException
		{	
			int records = 0;
			// retrieve the phone no (key)
			id = aFaculty.getId();
			// create the SQL delete statement
			String deleteFaculty = "DELETE FROM faculty WHERE CollegeId = ?";
			String deleteUser = "DELETE FROM users WHERE CollegeId = ?";
			
			// see if this customer already exists in the database
			try {
				
				PreparedStatement stmtFaculty = aConnection.prepareStatement(deleteFaculty);
				stmtFaculty.setLong(1, id);
				PreparedStatement stmtUser = aConnection.prepareStatement(deleteUser);
				stmtUser.setLong(1, id);
				Faculty.retrieve(id);  //used to determine if record exists for the passed Customer
	    		// if found, execute the SQL update statement
	    		records = stmtFaculty.executeUpdate();
	    		records = stmtUser.executeUpdate();
			}catch (NotFoundException e) {
				
				throw new NotFoundException("Faculty with ID " + id 
						+ " cannot be deleted, does not exist.");
				
			}catch (SQLException e) { 
				System.out.println(e);	
			}
			return records;
		}
	
}
