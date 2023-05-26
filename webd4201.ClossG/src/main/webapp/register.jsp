<jsp:include page="./utility/header.jsp">
	<jsp:param name="title" value="Register" />
	<jsp:param name="navBar" value="Register" />
</jsp:include> 
<%@ page import="java.util.*" %>
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");


if (session.getAttribute("student") != null){
	
	response.sendRedirect("dashboard.jsp?msg=Please signout before registering, a new student!");
	}
%>
<% List<String> sticky = (ArrayList<String>)session.getAttribute("sticky"); %>
<% List<String> error = (ArrayList<String>)session.getAttribute("err"); %>
<!-- <script src="./scripts/registerValidation.js"></script> -->
<h1 class="text-center">Register</h1>
<h4 class="text-center"><%if (request.getParameter("msg") != null){out.print(request.getParameter("msg"));}%></h4>

<div class="container text-center">
          
<form class="" method="post" action="Register" >
<div class="form-group ">

<div class="row justify-content-center">
		<div class="col-4">
	    <label for="inputId" >ID/Username</label>
	    <input type="text" class="form-control" name="inputId" value="<%try {out.print(sticky.get(0));}catch(Exception e){}%>" placeholder="Enter ID" required pattern="\d{9}" title="ID should be 9 digits">
	    <p id="idErr" class="hint-text"><% try{out.print(error.get(0));}catch(Exception e){} %> </p>
	    </div>
	    <div class="col-4">
	    <label for="inputEmail" >Email</label>
	    <input type="email" class="form-control " name="inputEmail" value="<%try {out.print(sticky.get(2));}catch(Exception e){}%>" placeholder="Enter Email" required>
 		<p id="emailErr" class="hint-text"><% try{out.print(error.get(2));}catch(Exception e){} %> </p>
 		</div>
 </div>
  
  
  
  <div class="row justify-content-center">
  <div class="col-md-4">
    <label for="inputFirst" >First Name</label>
    <input type="text" class="form-control " name="inputFirst" value="<%try {out.print(sticky.get(3));}catch(Exception e){}%>" placeholder="First Name" required pattern="\b([A-Z]+[-,a-z.']+)" title="Invaid first name">
    <p id="firstErr" class="hint-text"><% try{out.print(error.get(3));}catch(Exception e){} %> </p>
    
    </div>
    <div class="col-md-4 ">
    <label for="inputLast" >Last Name</label>
    <input type="text" class="form-control " name="inputLast" value="<%try {out.print(sticky.get(4));}catch(Exception e){}%>" placeholder="Last Name" required pattern="\b([A-Z]+[-,a-z.']+)" title="Invaid last name">
  	<p id="lastErr" class="hint-text"><% try{out.print(error.get(4));}catch(Exception e){} %> </p>
  </div>
  </div>
   
    <div class="row justify-content-center">
    <div class="col-md-3">
    <label for="inputPCode" >Program Code</label>
    	<input type="text" class="form-control" name="inputPCode" value="<%try {out.print(sticky.get(5));}catch(Exception e){}%>" placeholder="Program Code" required>
    	<p class="hint-text"><% try{out.print(error.get(5));}catch(Exception e){} %> </p>
    </div>
    <div class="col-md-3">
    	<label for="inputYear" >Year</label>
    	<input type="text" class="form-control " name="inputYear" value="<%try {out.print(sticky.get(6));}catch(Exception e){}%>" placeholder="Year" required>
    	<p class="hint-text"><% try{out.print(error.get(6));}catch(Exception e){} %> </p>
    </div>
    <!-- <div class="col-md-3 ">
    	<label for="inputMarks" >Marks</label>
    	<input type="text" class="form-control " name="inputMarks" placeholder="Marks" disabled>
  	</div> -->
  	</div>
  <div class="row justify-content-center">
  	<div class="col-md-5">
    	
    	<label for="inputPDesc" >Program Description</label>
    	<input type="text" class="form-control " name="inputPDesc" value="<%try {out.print(sticky.get(7));}catch(Exception e){}%>" placeholder="Program Description" required>
    	<p class="hint-text"><% try{out.print(error.get(7));}catch(Exception e){} %> </p>
    </div>
    </div>
  
  
  	
  	<div class="row justify-content-center">
  	<div class="col-md-4">
    <label for="inputPassword">Password</label>
    <input type="password" class="form-control" name="inputPassword" placeholder="Password" required>
   	<p class="hint-text"><% try{out.print(error.get(1));}catch(Exception e){} %> </p>
   </div>
  </div>
  
  
  <div class="row justify-content-center mt-3">
  <div class="col-md-4">
  <button type="submit" class="btn btn-dark btn-outline-success ">Submit</button>
  <button type="reset" class="btn btn-dark btn-outline-danger ">Cancel</button>
  </div>
  </div>
  </div>
</form>
<div class="row">
	<p class="text-light "> Already Registered? 
		<a href="login.jsp">Login</a>
	</p>
	</div>
</div>



<jsp:include page="./utility/footer.jsp">
	<jsp:param name="footerTitle" value="Index" />
</jsp:include>