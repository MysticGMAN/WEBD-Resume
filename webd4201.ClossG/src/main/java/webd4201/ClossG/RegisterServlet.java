package webd4201.ClossG;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession ses = null;    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		System.out.println("I am here in doPost Register.");
		ses = request.getSession(true);
		List<String> err = new ArrayList<String>();
		List<String> inLst = new ArrayList<String>();
		try {
			
			String username;
			String password;
			String email;
			String firstName;
			String lastName;
			String pCode;
			String year;
			String pDesc;
			
			Connection c = null;
			c = DatabaseConnect.initialize();
	        Student.initialize(c);
	        
	        username = request.getParameter("inputId").trim().toString();
	        try {
	        if (Student.verifyId(Long.parseLong(username))){
	        	inLst.add(0,username);
	        	err.add(0,"");
	        }else {
	        	err.add(0,"Invalid id! Must be 9 numbers");
	        	inLst.add(0,"");
	       	}
	        }catch (NumberFormatException e) {
	        	err.add(0,"Id must be numbers");
	        	inLst.add(0,"");
//	        	throw new InvalidUserDataException("Id must be numbers");
	        }
	        System.out.println(inLst.get(0));
	        
			password = request.getParameter("inputPassword").trim().toString();
			if (Student.verifyPassword(Student.hashPassword(password))) {
				err.add(1, "");
				inLst.add(1,"");
			}else {
				err.add(1, "Invalid password");
				inLst.add(1,"");
			}
			System.out.println(inLst.get(1));
			email = request.getParameter("inputEmail").trim().toString();
			if (Student.verifyEmail(email)){
	        	inLst.add(2,email);
	        	err.add(2,null);
	        }else {
	        	err.add(2,"Invalid email");
	        	inLst.add(2,null);
	       	}
			System.out.println(inLst.get(2));
			firstName = request.getParameter("inputFirst").trim().toString();
			if (Student.verifyName(firstName)){
	        	inLst.add(3,firstName);
	        	err.add(3,null);
	        }else {
	        	err.add(3,"First name must only be strings");
	        	inLst.add(3,null);
	       	}
			System.out.println(inLst.get(3));
			lastName = request.getParameter("inputLast").trim().toString();
			if (Student.verifyName(lastName)){
	        	inLst.add(4,lastName);
	        	err.add(4,null);
	        }else {
	        	err.add(4,"Last name must only be strings");
	        	inLst.add(4,null);
	       	}
			System.out.println(inLst.get(4));
			pCode = request.getParameter("inputPCode").trim().toString();
			inLst.add(5,pCode);
			System.out.println(inLst.get(5));
			year = request.getParameter("inputYear").trim().toString();
			inLst.add(6,year);
			System.out.println(inLst.get(6));
			pDesc = request.getParameter("inputPDesc").trim().toString();
			inLst.add(7,pDesc);
			System.out.println(inLst.get(7));
			System.out.println("After lengthy if block");
			
				if(err.isEmpty()) {
					System.out.println("in err is empty if");
					Student shithead = new Student(Long.parseLong(username), password, firstName, lastName, email, new Date(), new Date(), Student.DEFAULT_ENABLED_STATUS, Student.DEFAULT_TYPE, pCode, pDesc, Integer.parseInt(year) );
					if (shithead.create()) {
						ses.setAttribute("student", shithead);
						response.sendRedirect("dashboard.jsp");
						System.out.println("after redirect 1");
						//return;
					}
					}else {
						ses.setAttribute("err", err);
						ses.setAttribute("sticky", inLst);
						response.sendRedirect("register.jsp");
						System.out.println("after redirect 2");
						
				}
			
			
			
			} catch (InvalidUserDataException e)	{
				ses.setAttribute("msg", "" + e.getMessage() + " " + e.getStackTrace() + "");
				System.out.println("fail validation " + e.getMessage());
				System.out.println(e.getMessage() + " " + e.getStackTrace()[1].getLineNumber());
				e.printStackTrace();
				
			} catch (Exception e) {
				System.out.println("Exception fail validation " + e.getMessage());
				e.printStackTrace();
				//response.sendRedirect("register.jsp");
			}
	}

}
