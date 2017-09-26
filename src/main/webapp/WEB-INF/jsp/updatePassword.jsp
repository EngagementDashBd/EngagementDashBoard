<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>

<link rel="stylesheet"	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<!-- <link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/validationmsg.css">


<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
 <script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
 <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="css/allPage.css">
<link rel="stylesheet" href="css/error.css">
<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css"
	rel="stylesheet">
<title>New Employee</title>
    
    <!-- New Import -->
     <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/themes/infragistics/infragistics.theme.css" rel="stylesheet" />
    <link href="http://cdn-na.infragistics.com/igniteui/2017.1/latest/css/structure/infragistics.css" rel="stylesheet" />

    <script src="http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js"></script>
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>

 <!--    Ignite UI Required Combined JavaScript Files -->
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.core.js"></script>
    <script src="http://cdn-na.infragistics.com/igniteui/2017.1/latest/js/infragistics.lob.js"></script>

	    <!-- New Import -->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="script/custom.js" type="text/javascript"></script>
<script src="script/jquery.filer.min.js" type="text/javascript"></script>
<!-- <link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/css/bootstrap-datetimepicker.min.css" /> -->
<!-- <script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script> -->


<script type="text/javascript" src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<script src="script/validate.js"></script>
<style>
table {
  border-collapse: separate;
 
  border-spacing: 30px 0;
}
td{
   padding: 5px 0;
}
#content {
  border : 1px solid #000;
  padding : 5px;
}

</style>
<c:if test="${checkpass == 'success'}">
<script>
  alert("Changed Successfully!! Please Login");
</script>
</c:if>
<c:if test="${checkpass == 'failure'}">
<script>
  alert("Error Changing the password");
</script>
</c:if>
<c:if test="${checkpass == 'incorrect'}">
<script>
  alert("Incorrect Password!! Please Try Again!!");
</script>
</c:if>
<script type="text/javascript">
function goBack() {
    window.history.back();
}
</script>



</head>
<body>


	<div class="container" id="main">
		<center><h2><b> New Employee Register </b></h2></center>
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
						<form  action="checkPassword.htm" method="post" onsubmit = "return formValidation();">
							<table>
								
								<tr>
									<td><label for="Current Password">Current Password<span style="color:red;">*</span></label></td>
									<td><input type="password" class="textBox" name="oldPassword" id="oldPassword"  /></td>
									
								</tr>
								<tr class="break"><td colspan="2"></td></tr>		
								<tr>
									<td><label for="New Password">New Password<span style="color:red;">*</span></label></td>
									<td><input type="password" class="textBox" name="password" id="password" /></td>
									
								</tr>
								<tr class="break"><td colspan="2"></td></tr>		
								<tr>
									<td><label for="Confirm Password">Confirm Password<span style="color:red;">*</span></label></td>
									<td><input type="password" class="textBox" name="confirmPassword" id="confirmPassword" /></td>
									
								</tr>
								<tr class="break"><td colspan="2"></td></tr>		
							<tr>
									<td><input type="submit" class="btn btn-info dropdown-toggle" value="Change Password" id="changepwd"  /></td>
									<td><a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td>
								</tr>
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