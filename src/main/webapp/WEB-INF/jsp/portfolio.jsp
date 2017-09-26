<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->
<!-- 
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="css/allPage.css">
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>Attendance Tracker ~ Portfolio Details</title>
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<script src="javascript/jquery-min-3.1.1.js" type="text/javascript"></script>
<script src="javascript/bootstrap-min-3.3.7.js" type="text/javascript"></script>
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>

<script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<link rel="stylesheet" href="css/error.css">
<link rel="stylesheet" href="css/validationmsg.css">
<script>
$(document).ready(function() {
	
	$.validator.addMethod("pattern", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value."); 

	
	
	$("#portfolio").validate({
	
		 rules: {
				"portfolioName" : {
					 required:true,
					 pattern: /^[a-z A-Z]+$/,
					 minlength:3,
					 maxlength:30
				 },
				"portfolioDescription":{
		            	required:true,
		            	pattern: /^[a-z A-Z]+$/,
		            	maxlength:50
		            },
		           
		          
				
		   		},
			messages:{
				
				"portfolioName" : {
					required: 'Portfolio Name is required.',
					minlength: 'Portfolio Name should be minimum 3 characters.',
					maxlength: 'Portfolio Name should be maximum 30 characters.',
					pattern:'Portfolio Name must be in alphabets'
				},
				"portfolioDescription": {
						required: 'Portfolio Description is required.',
						maxlength: 'Portfolio Description should be maximum  50 characters.'
				},	
							
				
			},
		 errorContainer: $('#errorContainer'),
	     errorLabelContainer: $('#errorContainer ul'),
	     wrapper: 'li'
});

});
</script>

<script type="text/javascript">
function goBack() {
    window.history.back();
}
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





	<div class="container" id="main">
	<center><h2><b> New Portfolio Detail</b></h2></center>
		<div class="row">
			<div class="col-md-12">
			<br>
				<br>
				<br>
				<br>
			<div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>		
		<div id="content">
				
				<form action="portfolio.htm" id="portfolio" >
	
						<table>							
							<tr><td>Portfolio Name:</td><td><input class = "textBox" id = "nonDescription" type="text" name="portfolioName" required></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							
							<tr><td>Description:</td><td><textarea rows="4" cols="50" name="portfolioDescription" id = "portfolioDescription" class = "textBox"></textarea></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							
							<tr class="break"><td colspan="2"></td></tr>							
						<tr align="center"><td colspan="2"><Button class="btn btn-info dropdown-toggle" type="submit">Save</Button>&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
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