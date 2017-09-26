<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- <link rel="stylesheet" href="css/sideNav.css"> -->
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>Attendance Tracker ~ Update Project Details</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="css/allPage.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>

<script type="text/javascript" src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
</head>
<body>
<div class="container" id="main">
		<br>
		<br>
		<div class="row">
			<center><h2><b> UPDATE CARRER LEVEL DETAILS</b></h2></center>
			<div class="col-md-12">
			<br>
				<br>
				<br>
				<br>
			
				<div id="content">
				<br>
				<br>
				<form action="updateCareeeLevel.htm">
	
						<table>							
							<tr><td><label>Career Level:</label></td><td><input id = "textBox" type="text" name="level" value="${ CareerLevel.level}"></td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label>Designation:</label></td><td>
							 <input id = "textBox" type="text" name="designation" value="${CareerLevel.designation}"/>
							</td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label>Shift B Amount:</label></td><td>
							<input id = "textBox" type="text" name="shiftbAmount" value="${CareerLevel.shiftbAmount}"/>
							</td></tr>
							<tr class="break"><td colspan="2"></td></tr>
							<tr><td><label>Shift C Amount:</label></td><td>
							<input id = "textBox" type="text" name="shiftcAmount" value="${CareerLevel.shiftcAmount}"/>
							</td></tr>
							<tr class="break"><td colspan="2"></td></tr>							
							<tr align="center"><td ><Button class="btn btn-info dropdown-toggle" type="submit">Update details</Button></td><td><a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td></tr>
						</table>					
					</form>
				

				</div>
				<br>
</body>
</html>