<jsp:include page="./utility/header.jsp">
	<jsp:param name="title" value="Login" />
	<jsp:param name="navBar" value="Login" />
</jsp:include> 
<% 
if (session.getAttribute("firstname") != null){
		response.sendRedirect("dashboard.jsp?msg=You are already logged in! Ya silly sausage.");
	}
%>

<h1 class="text-center">Login</h1>
<h4 class="text-center"><%if (request.getParameter("msg") != null){out.print(request.getParameter("msg"));}%></h4>
<div class="container content-center text-center align-self-center">
          
<form method="post" action="Login" >
<div class="form-group ">
<div class="row justify-content-center">
<div class="col-md-6">
    <label for="inputId" >ID/Username</label>
    <input type="text" class="form-control " name="inputId" placeholder="Enter ID">
  </div>
  </div>
  </div>
  <div class="form-group ">
  <div class="row justify-content-center">
  	<div class="col-md-6">
    <label for="inputPassword">Password</label>
    <input type="password" class="form-control" name="inputPassword" placeholder="Password">
   </div>
  </div>
  </div>
  <div class="form-group ">
  <div class="row justify-content-center">
  <div class="col-md-4 ml-auto">
  <button type="submit" class="btn btn-dark btn-outline-success mt-3">Submit</button>
  <button type="reset" class="btn btn-dark btn-outline-danger mt-3">Cancel</button>
  </div>
  </div>
  </div>
</form>
<div class="row">
	<p class="text-light"> Not Registered? 
		<a href="register.jsp">Register</a>
	</p>
	</div>
</div>

<jsp:include page="./utility/footer.jsp">
	<jsp:param name="footerTitle" value="Index" />
</jsp:include>
