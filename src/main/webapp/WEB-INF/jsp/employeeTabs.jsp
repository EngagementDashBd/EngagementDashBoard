<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance Tracker</title>
</head>
<body>
    <body>
   <div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
				Attendance-Tracker</a>
		</div>
	 <ul class="nav navbar-nav pull-right">
			<li class="dropdown" id="logout"><a class="dropdown-toggle"
					href="#" data-toggle="dropdown" id="navLogout"><span class="glyphicon glyphicon-user">&nbsp${resource.employeeName}</span></a>
				<div class="dropdown-menu">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
	
	<button type="submit" class="btn btn-danger btn-block">Logout</button>
	</form>
	</div>
			</li>	
		</ul>
	</div>	 
	<div class="nav-side-menu">   
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">
				Attendance-Tracker</a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="redirect.htm?pageName=myProfile"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> My Profile </a></li>
			<li><a href="redirect.htm?pageName=myPersonaldetails"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>My PersonalDetails </a></li>	
			<li><a href="redirect.htm?pageName=timesheet"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span>TimeSheets </a></li>
			<c:if test = "${resource.level <= 9}">
				<li><a href="employeesUnderSupervisor.htm"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> My Team</a></li>
				<li><a href="employeesOnHub.htm"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Hub</a></li>
      			<li><a href="approve.htm"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Approve</a></li>
      			<li><a href="weeklyStatusReport.htm"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> WSR</a></li>
      			<li><a href="redirect.htm?pageName=report"><span class="glyphicon glyphicon-save-file" aria-hidden="true"></span> Reports</a></li>
       		</c:if>
       	</ul>
		<ul class="nav navbar-nav pull-right">
			<li class="dropdown" id="logout"><a class="dropdown-toggle"
					href="#" data-toggle="dropdown" id="navLogout"><span class="glyphicon glyphicon-user">&nbsp${resource.employeeName}</span></a>
				<div class="dropdown-menu">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
	
	<button type="submit" class="btn btn-danger btn-block">Logout</button>
	</form>
	</div>
			</li>
		</ul>
	</div>
</nav>
	
</body>
</html>