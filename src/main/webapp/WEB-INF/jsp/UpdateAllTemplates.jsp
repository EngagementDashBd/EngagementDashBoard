<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<title>Attendance Tracker ~ Update Template Details</title>
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
	$.validator.addMethod("alphabets", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value.");    
	
	$("#updatetemplate").validate({
	//	errorLabelContainer:$("#validationErrors"),


		rules: {
			 "projectName": {
				 required:true,
				 maxlength:32,
				 alphabets: /^[a-z A-Z]+$/
			 },
	         "projectDescription":{
	            	required:true,
	            	maxlength:50,
	            	 alphabets: /^[a-z A-Z]+$/
	            },
	           
	          "programId":{
	            	required:true
	            }
	          
			
	   		},
		messages:{
			
			"projectName": {
			
				required: 'Project Name is required.',
				minlength: 'Project Name should be minimum 3 characters.',
				maxlength: 'Project Name should be maximum 30 characters.'
			},
			
			"projectDescription": {
					required: 'Project Description is required.',
					maxlength: 'Project Description should be maximum  50 characters.'
			},	
						
			"programId": {
				required: 'Project Program is required.',
				
		}	
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
  alert("Project details added successfully");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Error..... Project already exists !!!");
</script>
</c:if>
	
	<div class="container" id="main">
		<br>
		<br>
		<div class="row">
			<center><h2><b> UPDATE TEMPLATE DETAILS</b></h2></center>
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
				<form action="updateTemplateDetails.htm" id="updatetemplate">
	
						<table>							
							<tr><td>Template Name:</td><td><input id = "textBox" type="text" name="templateName" value="${ template.templateName}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							
							
							<tr><td>Template File:</td><td><input id = "textBox" type="file" name="templateFile" value="${ template.templateFile}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							
							
							
							
							
							<tr><td><input type="hidden" name ="id" value="${ template.id}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>							
							<tr align="center"><td colspan="2" ><Button class="btn btn-info dropdown-toggle" type="submit">Update details</Button>&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
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
