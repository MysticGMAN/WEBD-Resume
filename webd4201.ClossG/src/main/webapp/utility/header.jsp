
<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"> 
<%@ page import="webd4201.ClossG.*"%> 
<%
	
	Student stud;
	String firstName;
	Long id;
	String loginT;
	String loginH;
	String registerT;
	String registerH;
	String dashboardH;
	
	if (session.getAttribute("student") != "" && session.getAttribute("student") != null){
		
		stud = (Student)session.getAttribute("student");
		firstName = stud.getFirstName();
		id = stud.getId();
		loginT = "Logout";
		loginH = request.getContextPath() + "/Logout";
		registerT = "Update";
		registerH = "update.jsp";
		dashboardH = "dashboard.jsp";

	} else {
		
		stud = null;
		firstName = null;
		id = null;
		loginT = "Login";
		loginH = "login.jsp";
		registerT = "Register";
		registerH = "register.jsp";
		dashboardH = "login.jsp";
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><%=request.getParameter("title") %></title>
<link rel="stylesheet" href="./css/bootstrap/bootstrap.css"/>

</head>
<body class="bg-dark text-light">
<header>
	<nav class="navbar navbar-dark bg-black">
	<a class="navbar-brand float-lg-right" href="index.jsp">
		<img src="./images/Closs.png" width="65" height="65" alt=""/>
		Closs Tech inc. - <%=request.getParameter("navBar") %>
	</a>
	<ul class="nav">
  <li class="nav-item">
    <a class="nav-link" href="index.jsp">Index</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=dashboardH%>"><%if (firstName != null){out.print(firstName);}%> Dashboard</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=loginH%>"><%=loginT%></a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<%=registerH%>"><%=registerT%></a>
  </li>
</ul>
	</nav>
</header>