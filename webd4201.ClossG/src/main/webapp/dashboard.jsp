<%@ page import="webd4201.ClossG.*"%> 
<jsp:include page="./utility/header.jsp">
	<jsp:param name="title" value="Dashboard" />
	<jsp:param name="navBar" value="Dashboard" />
</jsp:include> 
<% 
	if (session.getAttribute("student") == null){
		response.sendRedirect("login.jsp");
	}
	String dashboard =  (String)session.getAttribute("firstname")+" Dashboard";
 	
%>

<h1><%=dashboard %></h1>
<p class="text-center"><%if (request.getParameter("msg") != null ){out.print(request.getParameter("msg"));}%></p>


<jsp:include page="./utility/footer.jsp">
	<jsp:param name="footerTitle" value="Index" />
</jsp:include>
