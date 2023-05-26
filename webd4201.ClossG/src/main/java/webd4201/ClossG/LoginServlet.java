package webd4201.ClossG;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession ses = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("I am here in doPost Login.");
		ses = request.getSession(true);
		try {
			Long username;
			String password;
			
			
			Connection c = null;
			c = DatabaseConnect.initialize();
	        Student.initialize(c);
	        
	        
	        username = Long.parseLong(request.getParameter("inputId").trim());
	        
	        

			password = request.getParameter("inputPassword").trim().toString();

			
			
			
			Student stud = Student.authenticate(username, password);
			System.out.println("After Auth");
			ses.setAttribute("student", stud);
			ses.setAttribute("firstname", stud.getFirstName());
			System.out.println("after first set");
			ses.setAttribute("id", stud.getId());
			System.out.println("after last set");
			
			response.sendRedirect("dashboard.jsp");
			System.out.println("after redirect");	
				
			} catch (Exception e) {
				System.out.println("fail validation " + e.getMessage());
				response.sendRedirect("login.jsp?msg=Error! Credentials Are Incorrect!");
				
			}
		
		
		
		
	}

}
