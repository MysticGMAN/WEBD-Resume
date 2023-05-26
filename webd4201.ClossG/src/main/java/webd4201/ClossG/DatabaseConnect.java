package webd4201.ClossG;
/**
 * @author Grayson Closs
 *
 */

import java.sql.*;

public class DatabaseConnect {
	
	// connection string static variables 
		private static final String url = "jdbc:postgresql://127.0.0.1:5432/webd4201_db";
		private static final String user = "webd4201_admin";
		private static final String password = "webd4201_password";
		private static Connection aConnection;
		
		public static Connection initialize() {
			try {
				// load the JDBC Driver for PostGreSQL
				Class.forName("org.postgresql.Driver");
				// Create the Connection instance
				aConnection = DriverManager.getConnection(url, user, password);
				
			}
			// will occur if you did not import the PostGreSQL *.jar file with drivers
			catch (ClassNotFoundException e) { System.out.println(e); }
			//any other database exception (misnamed db, misnamed user, wrong password, etc)
			catch (SQLException sqle) { 
				
				System.out.println(sqle); 
			}
				
			return aConnection;
		}
		
		public static void terminate() {
			try { aConnection.close(); }
			catch (SQLException sqle) { 
				
				System.out.println(sqle); 
			}
		}

}
