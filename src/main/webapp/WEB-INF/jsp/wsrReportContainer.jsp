<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

 <link rel="stylesheet" type="text/css" href="css/dataTables.min.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="/resources/demos/style.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
	
	<link rel="stylesheet" type="text/css"	href="http://cdn.datatables.net/1.10.15/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
	<!-- <link rel="stylesheet" href="css/theme/simple.css" /> -->
 
<style>
.textBox
{
box-shadow:1px 1px 45px #333;
transition: 200ms all ease;
border:none;
padding: 5px 5px;

}
.textBox
{
margin:-5px 0 0 10px;
height:35px;
width:300px;
}
.textBox
{
margin:5px 0 0 20px;
box-shadow:1px 1px 45px #333;
transition: 200ms all ease;
border:none;
padding: 5px 5px;

}
</style>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WSR Report</title>
<script>
function getWSRbyProjectName() {
	
	$('#wsrcontent').load('getWsrRecords.htm', {programNames : $("#projectName").val()}	);
}
</script>

</head>
<body>

<div id="Tokyo" class="tabcontent" align="center">
		
	
		<font size="4"><b>Select by Project Name</b></font>		<select  id = "projectName" name="applicationsdsadfName" class="textBox" required onchange="getWSRbyProjectName()" >
											<option value="">---select a Project---</option>
											<c:forEach items="${ProjectNames}" var="ProjectNames">
											<option value= "${ProjectNames.projectName}" >${ProjectNames.projectName}</option>
											</c:forEach>
        							</select>
				
		
	</div>
		<div id="wsrcontent"> </div>
		<div id="Report" class="tabcontent" align="center">			</div>



</body>
</html>