<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<c:if test="${code == 'success'}">
<script>
  alert("Project details added successfully!!!!");
</script>
</c:if>
<c:if test="${code == 'failure'}">
<script>
  alert("Project Already Exists !!!");
</script>
</c:if>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Details</title>
<script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<%-- <script type="text/javascript" src="<c:url value="/script/admin/projectdetails.js"/>"></script> --%>
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.6.3/css/bootstrap-select.min.css" />
<!-- <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"> -->
<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet" href="css/error.css">
<link href="css/bootstrap-min-3.3.7.css" rel="stylesheet">
<link href="css/font-awesome-min4.7.0.css" rel="stylesheet">
<link rel="stylesheet" href="css/validationmsg.css">
	<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<script type="text/javascript">
function confirm_alert(node) {
    return confirm("Please click on OK to continue.");
}
$(document).ready(function() {
	$.validator.addMethod("alphabets", function(value, element, regexpr) {  
		
	     return this.optional( element ) || regexpr.test(value);
	   }, "Please enter a valid value.");    
	    
$("#addproject").validate({
    rules: {
        "projectName": {
            required: true,
            maxlength: 32,
            alphabets: /^[a-z A-Z]+$/
        },
        "projectDescription": {
            required: false,
            maxlength: 50,
            alphabets: /^[a-z A-Z]+$/
        },
        "programId": {
            required: true
        }
    },
    messages: {
        "projectName": {
            required: "Please enter  Project Name",
			maxlength: 'Project Name should be maximum 32 characters.',
			alphabets: 'Project Name should be in alphabets'
        },
        "projectDescription": {
           
			alphabets: 'Project Description should be in alphabets'
        },
        "programId": {
            required: "Please select  Project Program",
           
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
	<div class="container" id="main">
	<br>
	<center><h2><b> New Project Detail</b></h2></center>
	<br>
    <br>
    <div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>		
    <div class="top">
			<h1 id="title" class="hidden"><span id="logo">Attendance Tool</span></h1>
		</div>
		
	<form action = "addNewProject.htm" id="addproject"> 
			

			<table style="">
			<tr><td>Project Name <span style="color: red;">*</span></td><td><input type="text" name="projectName" id = "projectName" class = "textBox" required></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td>Project Description </td><td><textarea rows="4" cols="50" name="projectDescription" id = "projectDescription" class = "textBox" ></textarea></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
			<tr><td>Project Program <span style="color: red;">*</span></td><td><select  id = "programId" name="programId" class = "textBox" required>
									<option value="">--select an option---</option>
 										<c:forEach items="${programs}" var="program">
											<option value="${program.programId}">${program.programName}</option>
										</c:forEach>
									</select>	</td></tr>
										<tr class="break"><td colspan="2"></td></tr>
										<tr class="break"><td colspan="2"></td></tr>
			<tr align="center"><td colspan="2"><Button class="btn btn-info dropdown-toggle" type="submit" value="Save">Add details</Button>&nbsp;&nbsp;<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
			<tr class="break"><td colspan="2"></td></tr>
            </table> 
 
	
	</form>
	
	</div>

</body>

</html>