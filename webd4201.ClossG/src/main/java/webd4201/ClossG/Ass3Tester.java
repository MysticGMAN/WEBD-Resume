package webd4201.ClossG;

import java.sql.Connection;

public class Ass3Tester {

	public static void main(String[] args) {
		
//		try{  Student.terminate(); }catch(Exception e){System.out.println(e.toString());}
//        try{  DatabaseConnect.terminate(); }catch(Exception e){System.out.println(e.toString());}
		Connection c = null;
		c = DatabaseConnect.initialize();
        Student.initialize(c);
        
        System.out.println("AUTHENTICATION TESTS \n");
        
		// test student authenticator
        System.out.println("*******************************************");
        System.out.println("Should be Successful - Grayson");
        //testAuth(999999999, "100597686");
        try {
			new Student();
		} catch (InvalidUserDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("*******************************************");
        System.out.println("Should Fail - Wrong PW");
        testAuth(100000001, "mypassword2");
        System.out.println("*******************************************");
        System.out.println("Should Fail - User not found");
        testAuth(100000000, "mypassword");
        System.out.println("*******************************************");
        System.out.println("Should be Successul - Mike");
        testAuth(100111111, "password");
        System.out.println("*******************************************");
	}
	
	public static void testAuth (long id, String pw) {
		
		
		try {
			Student stud = Student.authenticate(id, pw);
			System.out.println("Authentication Successful");
			System.out.println(stud.getFirstName() + " " + stud.getLastName());
		 // close the database resources, if possible            
		          
			 
		}
		catch (NotFoundException nfe) { System.out.println("Authentication Failed: " + nfe.getMessage()); }
			
	}

}
