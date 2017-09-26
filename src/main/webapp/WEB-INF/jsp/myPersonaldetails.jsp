<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Personal Detail</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" href="css/allPage.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
	
<script src="script/profile.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">


<link href="css/jquery.filer.css" rel="stylesheet">
<link href="css/jquery.filer-dragdropbox-theme.css" rel="stylesheet">

<!--  Jquery Validation-->
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery-1.9.1.min.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/jquery.validate.min.js"/>"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.16.0/additional-methods.js"></script>
<link rel="stylesheet" href="css/validationmsg.css">
<link rel="stylesheet" href="css/allPage.css"/>
<script src="script/validate.js"></script>



<style>
td{
width:250px;

}
</style>

	<c:if test="${photoUpload == 'success'}">
		<script>
			alert("Profile Photo added successfully !!");
		</script>
	</c:if>
		<c:if test="${photoUpload == 'failure'}">
		<script>
			alert("Error updating Profile Photo!!");
		</script>
	</c:if>
<script>
	$(document).ready(function() {
		$.validator.addMethod("pattern", function(value, element, regexpr) {

			return this.optional(element) || regexpr.test(value);
		}, "Please enter a valid value.");

		$("#personaldetails").validate({

			rules : {
				"passportNo" : {
					required : false,
					pattern : /^[A-Z]{1}[0-9]{7}$/
				},
				"panNo" : {
					required : false,
					pattern : /^[A-Z]{5}\d{4}[A-Z]{1}$/
				},
				"contactNo" : {
					required : false,
					pattern : /^[0-9-+]+$/,
					minlength:10,
					maxlength:10

				},
				

			},
			messages : {
				"passportNo" : {
					pattern : 'Please provide correct Passport number'
				},
				"panNo" : {
                    pattern:'Please provide correct PAN ID'
				},
				"contactNo" : {
					pattern : 'Contact should be in numbers'

				},
				
			},
			errorContainer: $('#errorContainer'),
			errorLabelContainer: $('#errorContainer ul'),
			wrapper: 'li'

		});
			
		 
	
        $("#uploadPic" ).validate({
            rules: {
            	"fileData":{
                    required:true,
                    accept:'image/*'
                  }
            },
            
            messages: {
            	"fileData":{
                	required:'Please upload file.',
                	accept:'Please upload .jpg or .png or .jpeg file.'
                    
                }
            }
          
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
	<div class="container" id="main">
		<br> <br> <br>
		<center>
			<h2>
				<b>Update Personal Details</b>
			</h2>
		</center>
		<div class="row">
			<div class="col-md-12">
				<br> <br> <br> <br>
				<div id="errorContainer">
    <p>Please correct the following errors and try again:</p>
    <ul/>
	</div>		
				<div id="content" style="float:left; width:50%;">
					<form action="myPersonaldetails.htm" method="post" id="personaldetails">
						<table>
							
							<tr>
								<td><label for="Passport No">Passport No </td>
								<td><input class="textBox" type="text" name="passportNo" /></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td><label for="Pan No">Pan No </td>
								<td><input class="textBox" type="text" name="panNo" /></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr>
								<td><label for="Contact No">Contact No </td>
								<td><input class="textBox" type="text" name="contactNo" /></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							<tr class="break">
								<td colspan="2"></td>
							</tr>
							
							<tr align="center">
								<td colspan="2"><input class="btn btn-info dropdown-toggle"
									type="submit" value="Save" />&nbsp;&nbsp;
									<a href="javascript:goBack(); " class="btn btn-info dropdown-toggle">Cancel</a></td>
							</tr>
                            <tr class="break">
								<td colspan="2"></td>
							</tr>
						</table>
					</form>
					</div>
				<div id="content" style="float:right; width:50%;">
					<form action = "uploadPic.htm" method = "post" enctype="multipart/form-data" name="uploadPic" id="uploadPic" >
						<table>
						<tr>
						<td><label for="photo">Upload Photo</label></td>
						<td><input  name="fileData" id="fileData" type="file" /> </td>
						<td><input class="btn btn-info dropdown-toggle" id="uploadButton" type="submit" value="Upload"></td>
						</tr>
					</table>
					</form>
				</div>
				
			</div>
			
		</div>
	</div>
</body>
</html>
