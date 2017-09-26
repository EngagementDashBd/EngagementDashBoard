<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Panel</title>
</head>
<body>
      <div class="container-fluid">
	<!-- 	<div class="navbar-header">
			<a class="navbar-brand" href="#">
				Attendance-Tracker</a>
		</div> -->
	 <ul class="nav navbar-nav pull-right">
		<!-- 	<li class="dropdown" id="logout"> -->
		<a class="dropdown-toggle"
					href="#" data-toggle="dropdown" id="navLogout"><span class="glyphicon glyphicon-user">&nbsp${resource.employeeName}</span></a>
				<div class="dropdown-menu">
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
					
	</div>
	&nbsp &nbsp 
					<a href= "${logoutUrl}">
  						<img src="css/images/logout.jpg" alt="update employee" style="width:30px;height:28px;border:0;">
					</a>	
		</ul>
	</div>	 
            
    <div class="nav-side-menu">
	<div class="brand">
		<h3>ATTENDANCE TRACKER</h3>
	</div>
	<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
	<div class="menu-list">
		<ul id="menu-content" class="menu-content collapse out">
<!-- 			<li><a href="redirect.htm?pageName=adminPanel"><i
						class="fa fa-upload fa-lg"></i>New Employee Details</a></li> -->
			<li><a href="allEmployeeDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Employee Details</a></li>
			<!-- <li><a href="getAllPrograms.htm"><i
						class="fa fa-upload fa-lg"></i>New Project Details</a></li>
			 --><!-- <li><a href="redirect.htm?pageName=portfolio"><i
						class="fa fa-upload fa-lg"></i> Add New Portfolio Details</a></li> -->
		<li><a href="allPortfolioDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Portfolio Details</a></li> 
						<!-- <li><a href="redirect.htm?pageName=portfoliocheck"> <i
						class="fa fa-users fa-lg"></i> Portfolio Details</a></li> -->
			<li><a href="allProgramDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Program Details</a></li>
			<li><a href="allProjectDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Project Details</a></li>
			
			<li><a href="file.htm"> <i
						class="fa fa-users fa-lg"></i>File-Data</a></li> 
			
			<li><a href="allTechnologyDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Technology Details</a></li>
			<!-- <li><a href="getportfolio.htm"><i
						class="fa fa-upload fa-lg"></i> Add New Program Details</a></li>
			 -->
			 <li><a href="employeesOnHub.htm"><i
						class="fa fa-users fa-lg"></i> Hub</a></li>
			 <li data-toggle="collapse" data-target="#service" class="collapsed">
					<a href="statistics.htm"><i class="fa fa-pie-chart fa-lg"></i> Statistics</a></li>
			<li data-toggle="collapse" data-target="#new" class="collapsed">
					<a href="redirect.htm?pageName=report"><i class="fa fa-file-excel-o fa-lg"></i> Generate
						Reports</a></li>
		 </ul>
	</div>
	
</div>
</body>
</html>