<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%-- <%@include file = "adminTabs.jsp"%> --%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<script src="javascript/jquery-min-3.1.1.js" type="text/javascript"></script>
<script src="javascript/bootstrap-min-3.3.7.js" type="text/javascript"></script>
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>Attendance Tracker ~ Update Portfolio Details</title>
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<link rel="stylesheet" href="css/allPage.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<link rel="stylesheet" href="css/error.css">
<link rel="stylesheet" href="css/validationmsg.css">
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<script>
$(document).ready(function() {
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 

	
	$("#updateprogram").validate({
	
		 rules: {
				"programName" : {
					 required:true,
					 pattern: /^[a-z A-Z]+$/,
					 minlength:3,
					 maxlength:30
				 },
				 
				 "portfolioId" : {
				      required:true
				 },
				"programDescription":{
		            	required:true,
		            	pattern: /^[a-z A-Z]+$/,
		            	maxlength:50
		            },
		           
		          
				
		   		},
			messages:{
				
				"programName" : {
					required: 'Program Name is required.',
					minlength: 'Program Name should be minimum 3 characters.',
					maxlength:' Program Name should be maximum 30 characters.'
				},
				
				
				 "portfolioId" : {
				      required:'Please choose Portfolio Name',
				 },
				 
				"programDescription": {
						required: 'Program Description is required.',
						maxlength: 'Program Description should be maximum  50 characters.'
				},	
							
				
			},
			
			errorContainer: $('#errorContainer'),
		     errorLabelContainer: $('#errorContainer ul'),
		     wrapper: 'li'
			
});

});

</script>
</head>
<body>
<c:if test="${code == 'success'}">
<script>
  alert("Portfolio details added successfully");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error..... Portfolio already exists !!!");
</script>
</c:if>
	<!-- <div class="nav-side-menu">
		<div class="brand">
			<h3>ATTENDANCE TRACKER</h3>
		</div>
		<i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse"
			data-target="#menu-content"></i>
		<div class="menu-list">
			<ul id="menu-content" class="menu-content collapse out">
			<li><a href="redirect.htm?pageName=employeeDetails"><i
						class="fa fa-upload fa-lg"></i>  New Employee Details</a></li>
			
				<li><a href="allEmployeeDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Employee Details
				</a></li>
				
				 <li><a href="getAllPrograms.htm"><i
						class="fa fa-upload fa-lg"></i>New Project Details
				</a></li>
				<li><a href="#"><i
						class="fa fa-upload fa-lg"></i> Add New Portfolio Details</a></li>
				<li><a href="allPortfolioDetails.htm"> <i
						class="fa fa-users fa-lg"></i> Portfolio Details
				</a></li>
				<li><a href="getportfolio.htm"><i
						class="fa fa-upload fa-lg"></i> Add New Program Details</a></li>
				<li data-toggle="collapse" data-target="#service" class="collapsed">
					<a href="statistics.htm"><i class="fa fa-pie-chart fa-lg"></i> Statistics</a>
				</li>


				<li data-toggle="collapse" data-target="#new" class="collapsed">
					<a href="redirect.htm?pageName=report"><i class="fa fa-file-excel-o fa-lg"></i> Generate
						Reports</a>
				</li>

				<li><a href="redirect.htm?pageName=reminder"> <i class="fa fa-envelope-open-o fa-lg"></i>
						Reminder
				</a></li>

			</ul>
		</div>
	</div>
 -->	<div class="container" id="main">
 <br>
 <br>
	<center><h2><b>UPDATE PROGRAM </b></h2></center>
		<div class="row">
			<div class="col-md-12">
			<br>
				<br>
				<div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>		
				<div id="content">
				<br>
				<br>
				<form action="updateProgram.htm" id="updateprogram">
	
						<table>							
							<tr><td>Program Name:</td><td><input id = "textBox" type="text" name="programName" value="${program.programName}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td>Description:</td><td><textarea rows="4" cols="50" name="description" id = "description" class = "textBox">${program.description}</textarea></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><b>Portfolio:</b></td>
								<td>
									<select  id = "portfolioId" name="portfolioId" class="textBox" required>
											<option value="">---select a portfolio---</option>
											<c:forEach items="${portfolios}" var="portfolio">
 											<c:if test = "${portfolio.portfolioName != portfolioName}">
        										<option value="${portfolio.portfolioId}">${portfolio.portfolioName}</option>
        									 </c:if>
        									 <c:if test = "${portfolio.portfolioName == portfolioName}">
        										<option value="${portfolio.portfolioId}" selected>${portfolio.portfolioName}</option>
        									 </c:if>
        									 
										</c:forEach>
									</select>			  

								</td></tr>
								<tr class="break"><td colspan="2"></td></tr>
							
							<tr><td><input type="hidden" name ="programId" value="${program.programId}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>	
							<tr class="break"><td colspan="2"></td></tr>							
							<tr align="center"><td colspan="2"><Button class="btn btn-info dropdown-toggle" type="submit">Update details</Button>&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
						</table>					
					</form>
				

				</div>
				<br>
				<br> 
			</div>
		</div>
	</div>
</body>
</html>